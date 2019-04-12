package com.htstudio.core.util;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

public class ShareUtil {
    /**
     * Open app in Google Play
     * @param context
     */
    public static void openAppInStore(Context context) {
        final String appPackageName = context.getPackageName();
        try {
            context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + appPackageName)).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
        } catch (Exception e) {
            context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + appPackageName)).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
        }
    }

    /**
     * Share Image
     * @param context
     * @param path : absolute file path
     */
    public static void shareImage(Context context, String path){
        if (path == null || path.equals("")) return;
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.putExtra(Intent.EXTRA_STREAM, Uri.parse(path));
        intent.setAction(Intent.ACTION_SEND);
        context.startActivity(Intent.createChooser(intent, "Chia sẻ"));
    }

    /**
     * Share Video
     * @param context
     * @param path : absolute file path
     */
    public static void shareVideo(Context context,String path) {
        if (path == null || path.equals("")) return;
        Intent sendIntent = new Intent();
        sendIntent.setType("video/*");
        sendIntent.putExtra(Intent.EXTRA_STREAM, (Uri.parse(path)));
        sendIntent.setAction(Intent.ACTION_SEND);
        context.startActivity(Intent.createChooser(sendIntent, "Chia sẻ"));
    }

    public static void shareText(Context context,String text) {
        if (text == null || text.equals("")) return;
        Intent sendIntent = new Intent();
        sendIntent.setType("text/plain");
        sendIntent.putExtra(Intent.EXTRA_STREAM, text);
        sendIntent.setAction(Intent.ACTION_SEND);
        context.startActivity(Intent.createChooser(sendIntent, "Chia sẻ"));
    }
}
