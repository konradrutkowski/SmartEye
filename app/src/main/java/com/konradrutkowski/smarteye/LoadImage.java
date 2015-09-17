package com.konradrutkowski.smarteye;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

/**
 * Created by Xooq on 2015-01-16.
 */
public class LoadImage {
    public static Bitmap getBitmap(String filePath) {
        int reqWidth = PhotoURI.width;
        int reqHeight = PhotoURI.height;
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(filePath,options);
        options.inSampleSize = CalculateSize.calculateInSampleSize(options, reqWidth, reqHeight);
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeFile(filePath,options);
    }
}
