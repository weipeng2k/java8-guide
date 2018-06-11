package com.murdock.books.java8guide;

import org.junit.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.Set;

/**
 * @author weipeng2k 2018年03月18日 下午14:53:02
 */
public class ZoneIdTest {

    @Test
    public void create() {
        ZoneId zoneId  = ZoneId.of("Europe/Paris");
        System.out.println(zoneId);

        LocalDateTime now = LocalDateTime.now();
        ZonedDateTime zonedDateTime = now.atZone(zoneId);
        System.out.println(zonedDateTime);
    }

    @Test
    public void zone_ids() {
        Set<String> availableZoneIds = ZoneId.getAvailableZoneIds();
        availableZoneIds.stream().filter(s -> s.startsWith("Asia")).forEach(System.out::println);
    }

    @Test
    public void offset() {
        LocalDateTime now = LocalDateTime.now(ZoneId.of("Asia/Shanghai"));
        ZoneOffset zoneOffset = ZoneOffset.ofHours(5);

        OffsetDateTime of = OffsetDateTime.of(now, zoneOffset);
        System.out.println(of);
    }
}
