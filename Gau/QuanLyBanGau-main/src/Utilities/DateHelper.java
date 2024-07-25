/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utilities;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Date;

import static java.time.temporal.ChronoUnit.DAYS;

/**
 *
 * @author Administrator
 */
public class DateHelper {

    public static String YMDtoDMy(String YMDdate) {
        String[] splitDate = YMDdate.split("-");
         return (splitDate[2] + "/" + splitDate[1] + "/" + splitDate[0]);
    }

    public static int getYearDiffFromCurrent(LocalDate startDate) {
        LocalDate now = LocalDate.now();
        int yearDiff = Math.abs(now.getYear() - startDate.getYear());
        return yearDiff;
    }

    public static long getDateDiffFromCurrent(LocalDate compareDate) {
        LocalDate dateNow = LocalDate.now();
        long daysBetween = DAYS.between(dateNow, compareDate);
        return daysBetween;
    }

    public static Date getDate(String s) {
        LocalDate local = LocalDate.parse(s, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        return Date.from(local.atStartOfDay(ZoneId.systemDefault()).toInstant());

    }

    public static java.sql.Date LocalDateToSQL(LocalDate local) {
        System.out.println("LocalDate:" + local + "to SQLDate: " + java.sql.Date.valueOf(local));
        return java.sql.Date.valueOf(local);
    }

    public static Date LocalDateToDate(LocalDate local) {
        return Date.from(local.atStartOfDay(ZoneId.systemDefault()).toInstant());
    }

    public static LocalDate StringToLocalDate(String s) {
        return LocalDate.parse(s, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }

    public static Date getDate(String s, String pattern) {
        DateTimeFormatter format;
        try {
            format = DateTimeFormatter.ofPattern(pattern);
            System.out.println("Sai định dạng ngày tháng. Sẽ chuyển sang sử dụng format mặc định");
        } catch (DateTimeParseException e) {
            format = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        }
        try {
            LocalDate local = LocalDate.parse(s, format);
            return Date.from(local.atStartOfDay(ZoneId.systemDefault()).toInstant());
        } catch (DateTimeParseException e) {
            System.out.println("Lỗi chuyền đổi định dạng ngày tháng");
            return null;
        }
    }

    public static String getString(Date date) {
        return date.toInstant().atOffset(ZoneOffset.UTC).format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }

    public static String getString(java.sql.Date date) {
        return date.toLocalDate().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }

    public static String getString(Date date, String pattern) {
        try {
            return date.toInstant().atOffset(ZoneOffset.UTC).format(DateTimeFormatter.ofPattern(pattern));
        } catch (DateTimeParseException e) {
            System.out.println("Sai định dạng ngày tháng. Sẽ chuyển sang sử dụng format mặc định");
            return date.toInstant().atOffset(ZoneOffset.UTC).format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        }

    }

    public static String getCurrentTime() {
        LocalDateTime local = LocalDateTime.now();
        String s = local.format(DateTimeFormatter.ofPattern("HH:mm:ss"));
        return s;
    }

    public static String getCurrentTime(String pattern) {
        LocalDateTime local = LocalDateTime.now();
        try {
            return local.format(DateTimeFormatter.ofPattern(pattern));
        } catch (DateTimeParseException e) {
            System.out.println("Sai định dạng ngày tháng. Sẽ chuyển sang sử dụng format mặc định");
            return local.format(DateTimeFormatter.ofPattern("HH:mm:ss"));
        }
    }

    public static LocalDateTime getFullDateTime() {
        return  LocalDateTime.now();
    }
}
