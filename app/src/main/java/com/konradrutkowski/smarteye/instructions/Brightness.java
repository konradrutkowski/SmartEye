package com.konradrutkowski.smarteye.instructions;

import android.graphics.Bitmap;
import android.graphics.Color;

/**
 * Created by Xooq on 2015-01-19.
 */
public class Brightness extends PictureOperation {
    ///Bitmap src;
    Bitmap bmp;

    int type;
    float percent;

    Brightness(float percent) {
        this.percent = percent;
    }

    public void bitmapOperation(Bitmap src) {
        int width = src.getWidth();
        int height = src.getHeight();
        bmp = Bitmap.createBitmap(width, height, src.getConfig());

        int A, R, G, B;
        int pixel;

        for (int x = 0; x < width; ++x) {
            for (int y = 0; y < height; ++y) {
                pixel = src.getPixel(x, y);
                A = Color.alpha(pixel);
                R = Color.red(pixel);
                G = Color.green(pixel);
                B = Color.blue(pixel);

                R = (int) (R * (1 + percent));
                if (R > 255) R = 255;

                G = (int) (G * (1 + percent));
                if (G > 255) G = 255;

                B = (int) (B * (1 + percent));
                if (B > 255) B = 255;

                bmp.setPixel(x, y, Color.argb(A, R, G, B));
            }
        }
    }
}
