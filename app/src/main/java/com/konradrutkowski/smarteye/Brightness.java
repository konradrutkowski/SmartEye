package com.konradrutkowski.smarteye;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.util.Log;

/**
 * Created by Xooq on 2015-01-19.
 */
public class Brightness implements Pixz{
    ///Bitmap src;
    Bitmap bmOut;

    int type;
    float percent;
    Brightness(float percent){
        this.percent = percent;
    }

    public void bitmapOperation(Bitmap src){
        Log.d("HEHE", "BMP DOSTUFF");
        int width = src.getWidth();
        Log.d("HEHE", "width"+width+"" );

        int height = src.getHeight();
        Log.d("HEHE", "h"+height+"");
        bmOut = Bitmap.createBitmap(width, height, src.getConfig());

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

                bmOut.setPixel(x, y, Color.argb(A, R, G, B));

            }
        }

    }

    public Bitmap getBmpOut() {
        Log.d("HEHE", "ColorUPTASK ODDAJE BMP");
        if (bmOut == null){
            Log.d("HEHE", "ColorUPTASK CZY JA MAM NULLA?");
        }
        return bmOut;
    }
}
