package com.icia.board.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class UtilClass {
    // 날짜 데이터 변경용 메서드(년 월 일 시 분 초)
    public static String dateTimeFormat(LocalDateTime dateTime) {
        if (dateTime == null)
            return null;
        else
            return dateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }
}
