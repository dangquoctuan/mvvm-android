package com.htstudio.core.util;

import android.content.Context;
import android.text.format.Formatter;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class FileUtil {
    public static String getFileSize(Context context, String path){
        File file  = new File(path);
        if (!file.exists()) return "";
        return Formatter.formatFileSize(context, file.length());
    }

    public static String getDateLastModified(String path){
        File file  = new File(path);
        if (!file.exists()) return "";
        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss", Locale.US);
        return format.format(new Date(file.lastModified()));
    }


}
