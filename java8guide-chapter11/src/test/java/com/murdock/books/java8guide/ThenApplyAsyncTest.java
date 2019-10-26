package com.murdock.books.java8guide;

import org.junit.Assert;
import org.junit.Test;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author weipeng2k 2018年05月30日 下午14:02:22
 */
public class ThenApplyAsyncTest {

    /**
     * <pre>
     * 这里可以看到进行thenApply计算时，会根据方法要求返回一个CompletableFuture
     * 在这个方法中会获取当前CompletableFuture的结果，然后进行Function计算，并将计算的结果
     * 设置到返回的CompletableFuture中
     * </pre>
     */
    @Test
    public void then_apply_async() {
        CompletableFuture<String> message = CompletableFuture.completedFuture("message").thenApplyAsync(result -> {
            System.out.println(Thread.currentThread());
            return result.toUpperCase();
        });

        Assert.assertEquals("MESSAGE", message.join());
    }

    /**
     * thenApplyAsync类似的方法，在使用executor参数时，
     * Function将会在线程中执行函数
     */
    @Test
    public void then_apply_async_2() {
        Executor executor = Executors.newSingleThreadExecutor();

        CompletableFuture<String> source = new CompletableFuture<>();

        CompletableFuture<String> thenApplyAsync = source.thenApplyAsync(sv -> {
            System.out.println(Thread.currentThread() + " accept result");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return sv.toUpperCase();
        }, executor);

        Thread thread = new Thread(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread() + " set value");
            source.complete("message");
        });
        thread.setName("SetThread");
        thread.start();

        System.out.println(thenApplyAsync.join());
    }

    @Test
    public void then_apply_async_direct() {
        System.out.println("test thread " + Thread.currentThread());
        CompletableFuture<String> make = CompletableFutureUtils.make("io", 1000, "DONE");
        DelegateExecutor delegateExecutor = new DelegateExecutor(Executors.newSingleThreadExecutor());
        CompletableFuture<Integer> integerCompletableFuture = make.thenApplyAsync((value) -> {
            System.out.println("func thread " + Thread.currentThread());
            return value.length();
        }, delegateExecutor);

        delegateExecutor.setDelegate(new DirectExecutor());
        System.out.println(integerCompletableFuture.join());
    }

    @Test
    public void then_apply_sync() {
        System.out.println("test thread " + Thread.currentThread());
        CompletableFuture<String> make = CompletableFutureUtils.make("io", 1000, "DONE");
        CompletableFuture<Integer> integerCompletableFuture = make.thenApply((value) -> {
            System.out.println("func thread " + Thread.currentThread());
            return value.length();
        });

        System.out.println(integerCompletableFuture.join());
    }

    @Test
    public void then_apply_sync_compose() {
        System.out.println("test thread " + Thread.currentThread());
        CompletableFuture<String> make = CompletableFutureUtils.make("io", 1000, "DONE");

        CompletableFuture<Integer> completableFuture = make.thenCompose((content) -> {
            return CompletableFuture.supplyAsync(() -> {
                System.out.println("func thread " + Thread.currentThread());
                return content.length();
            }, new DirectExecutor());
        });

        System.out.println(completableFuture.join());
    }

    @Test
    public void then_apply_two() {
        System.out.println("test thread " + Thread.currentThread());
        CompletableFuture<String> make = CompletableFutureUtils.make("io", 1000, "DONE");

        CompletableFuture<Integer> completableFuture = CompletableFuture.completedFuture(make).thenCompose((m) -> {
            System.out.println("func thread " + Thread.currentThread());
            String join = m.join();
            System.out.println("do un serialize " + Thread.currentThread());
            return CompletableFuture.completedFuture(join.length());
        });

//        System.out.println(completableFuture.join());
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void g_m() {
        g().join();
    }

    private CompletableFuture<Integer> g() {
        System.out.println("test thread " + Thread.currentThread());
        CompletableFuture<String> make = CompletableFutureUtils.make("io", 1000, "DONE");

        CompletableFuture<Integer> completableFuture = make.thenCompose(v -> {

            return CompletableFuture.supplyAsync(() -> {
                System.out.println("func thread " + Thread.currentThread());
                return v.length();
            }, new DirectExecutor());
        });

        return completableFuture;
    }


    @Test
    public void then_apply_async_callback() {
        System.out.println("test thread " + Thread.currentThread());
        CompletableFuture<String> make = CompletableFutureUtils.make("io", 1000, "DONE");
        CompletableFuture<Integer> integerCompletableFuture = make.thenApplyAsync((value) -> {
            System.out.println("func thread " + Thread.currentThread());
            // un serial
            return value.length();
        }, /**callback**/Executors.newSingleThreadExecutor());

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void then_apply_sync_cf() {
        System.out.println("test thread " + Thread.currentThread());
        // invoke
        DelayedExecutor delayedExecutor = new DelayedExecutor();

        // invoke IO create make
        CompletableFuture<String> make = CompletableFutureUtils.make("io", 1000, "DONE");

        // invoke and return future
        CompletableFuture<Integer> integerCompletableFuture = make.thenApplyAsync((value) -> {
            // happen biz thread
            System.out.println("func thread " + Thread.currentThread());
            return value.length();
        }, delayedExecutor);


        // add function, or callback filter onResponse
        CompletableFuture<Void> completableFuture = integerCompletableFuture.thenAccept(v -> {
            System.out.println("next func thread " + Thread.currentThread());
            System.out.println("got " + v);
        });

        // call this while execute in current thread
        delayedExecutor.release();
        System.out.println(completableFuture.join());
    }

    @Test
    public void then_apply_future() {
        System.out.println("test thread " + Thread.currentThread());
        CompletableFuture<String> make = CompletableFutureUtils.make("io", 1000, "DONE");
        DelayedExecutor delayedExecutor = new DelayedExecutor();
        make.thenRunAsync(delayedExecutor::release, Executors.newSingleThreadExecutor());

        CompletableFuture<Integer> completableFuture = make.thenApplyAsync((value) -> {
            System.out.println("func thread " + Thread.currentThread());
            return value.length();
        }, delayedExecutor);

        delayedExecutor.release();
        System.out.println(completableFuture.join());

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static final class DelegateExecutor implements Executor {
        private volatile Executor delegate;

        public DelegateExecutor(Executor delegate) {
            this.delegate = delegate;
        }

        public void setDelegate(Executor delegate) {
            this.delegate = delegate;
        }

        @Override
        public void execute(Runnable command) {
            delegate.execute(command);
        }
    }

    private static final class DelayedExecutor implements Executor {

        private Runnable command;

        private Lock lock = new ReentrantLock();

        private Condition right = lock.newCondition();

        private AtomicBoolean status = new AtomicBoolean();

        @Override
        public void execute(Runnable command) {
            lock.lock();
            try {
                this.command = command;
                right.signalAll();
            } finally {
                lock.unlock();
            }
        }

        void release() {
            lock.lock();
            try {
                while (command == null) {
                    try {
                        right.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                if (status.compareAndSet(false, true)) {
                    command.run();
                }
            } finally {
                lock.unlock();
            }
        }
    }

    class DirectExecutor implements Executor {

        @Override
        public void execute(Runnable command) {
            command.run();
        }

        @Override
        public String toString() {
            return "MoreExecutors.directExecutor()";
        }
    }
}
