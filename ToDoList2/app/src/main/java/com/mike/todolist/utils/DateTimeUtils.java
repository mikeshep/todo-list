package com.mike.todolist.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class DateTimeUtils {
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
    private static final SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm", Locale.getDefault());

    public static String formatDate(Calendar calendar) {
        return dateFormat.format(calendar.getTime());
    }

    public static String formatTime(Calendar calendar) {
        return timeFormat.format(calendar.getTime());
    }
}
