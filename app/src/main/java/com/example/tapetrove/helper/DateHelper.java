package com.example.tapetrove.helper;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class DateHelper {
  public static String getCurrentDate() {
    DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss", Locale.getDefault());
    Date date = new Date();
    return dateFormat.format(date);
  }
  public static String getReturnDue() {
    DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd", Locale.getDefault());
    Calendar calendar = Calendar.getInstance();
    calendar.add(Calendar.DAY_OF_YEAR, 7); // Tambah 7 hari dari tanggal saat ini
    Date returnDate = calendar.getTime();
    return dateFormat.format(returnDate);
  }
}
