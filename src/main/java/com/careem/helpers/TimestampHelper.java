package com.careem.helpers;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by deepak on 26/02/17.
 */
public class TimestampHelper {
    public static String getTimeStamp() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        String string = dateFormat.format(new Date());
        return string;
    }
}
