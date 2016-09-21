package com.konradrutkowski.smarteye.instructions;

import android.graphics.Bitmap;

/**
 * Created by Konrad Rutkowski on 2015-01-13.
 */
public abstract class PictureOperation implements Operation {
    Bitmap bmp;

    @Override
    public Bitmap getProcessedBitmap() {
        return bmp;
    }
}
