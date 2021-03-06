package utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class LocalDateTimeMapper {
    private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public static LocalDateTime map(String dateTime) {
        if (dateTime == null) return null;
        return LocalDateTime.parse(dateTime, formatter);
    }
}
