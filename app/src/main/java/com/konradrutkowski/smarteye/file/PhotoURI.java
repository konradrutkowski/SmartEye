package com.konradrutkowski.smarteye.file;

import android.os.Environment;

/**
 * Created by Xooq on 2015-01-15.
 */
public class PhotoURI {
    public static String URI = Environment.getExternalStorageDirectory().getPath() + "/test3.jpg";
    public static int height = 1024;
    public static int width = 786;

    public static String getURI() {
        return PhotoURI.URI;
    }

    public static void setURI(String URI) {
        PhotoURI.URI = URI;
    }
}

