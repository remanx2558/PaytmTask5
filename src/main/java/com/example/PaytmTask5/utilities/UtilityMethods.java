package com.example.PaytmTask5.utilities;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class UtilityMethods {

    public static String get_current_time() {
        return new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(Calendar.getInstance().getTime());
    }
}