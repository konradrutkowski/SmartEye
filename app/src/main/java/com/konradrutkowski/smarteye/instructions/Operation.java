package com.konradrutkowski.smarteye.instructions;

import android.graphics.Bitmap;

/**
 * Created by Konrad on 21.09.2016.
 */
public interface Operation {
    void bitmapOperation(Bitmap src);

    Bitmap getProcessedBitmap();
}