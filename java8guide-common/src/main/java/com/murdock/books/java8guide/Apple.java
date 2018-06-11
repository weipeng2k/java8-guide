package com.murdock.books.java8guide;

/**
 * @author weipeng2k 2018年01月13日 下午21:20:03
 */
public class Apple {

    /**
     * 重量，克
     */
    private int weight;
    /**
     * 颜色
     */
    private Color color;

    public Apple(int weight, Color color) {
        this.weight = weight;
        this.color = color;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public boolean isBig() {
        return weight > 150;
    }

    @Override
    public String toString() {
        return "Apple{" +
                "weight=" + weight +
                ", color=" + color +
                '}';
    }
}
