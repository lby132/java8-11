package me.whiteship;

import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.GregorianCalendar;

public class App3 {

    public static void main(String[] args) {
        final Date date = new Date();
        final GregorianCalendar calendar = new GregorianCalendar();
        final SimpleDateFormat dateFormat = new SimpleDateFormat();

        //Instant는 기계용 데이터임. 시간을 재거나 메소드 실행시간을 비교할때 사용.
        final Instant instant = Instant.now();
        System.out.println("instant = " + instant); // UTC, GMT 기준
        System.out.println("instant = " + instant.atZone(ZoneId.of("UTC"))); // UTC로 표현. UTC와 GMT시간은 같다.

        //현재 내가 있는곳과 시간을 나타내줌.
        final ZoneId zone = ZoneId.systemDefault();
        System.out.println("zone = " + zone);
        final ZonedDateTime zonedDateTime = instant.atZone(zone);
        System.out.println("zonedDateTime = " + zonedDateTime);
        //출력결과 = zonedDateTime = 2022-12-21T18:11:49.123541+09:00[Asia/Seoul]

        LocalDateTime now = LocalDateTime.now();
        LocalDateTime birthDay = LocalDateTime.of(1982, Month.JULY, 15, 0, 0, 0);

        LocalDate today = LocalDate.now();
        LocalDate thisYearBirthday = LocalDate.of(2023, Month.JUNE, 12);
        ZonedDateTime.now(ZoneId.of("Asia/Seoul"));

        //Period는 날짜와 날짜끼리 얼마나 차이가 있는지 비교함. (휴먼용)
        final Period period = Period.between(today, thisYearBirthday);
        final Period until = today.until(thisYearBirthday);
        System.out.println("period = " + period.get(ChronoUnit.DAYS));
        System.out.println("until = " + until.get(ChronoUnit.DAYS));

        //Duration도 날짜와 날짜끼리 얼마나 차이가 있는지 비교함. (머신용)
        Instant now1 = Instant.now();
        Instant plus = now1.plus(10, ChronoUnit.SECONDS);
        final Duration between = Duration.between(now1, plus);
        System.out.println("between = " + between.getSeconds());

        final LocalDateTime now2 = LocalDateTime.now();
        final DateTimeFormatter MMddyyyy = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        System.out.println("now2.format(MMddyyyy) = " + now2.format(MMddyyyy));

        final LocalDate parse = LocalDate.parse("07/15/1982", MMddyyyy);
        System.out.println("parse = " + parse);

    }
}
