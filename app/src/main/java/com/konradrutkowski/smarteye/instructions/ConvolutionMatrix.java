package com.konradrutkowski.smarteye.instructions;


import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Matrix;

/**
 * Created by Xooq on 2015-01-12.
 */
public class ConvolutionMatrix {
    private static final int MATRIX_SIZE = 3;

    private static int validation(int color) {
        if (color > 255)
            return 255;
        else if (color < 0)
            return 0;
        else
            return color;
    }

    public static Bitmap convolute(Bitmap bitmap, Matrix matrix, float factor, int offset) {
        float[] matrixOvSize = new float[MATRIX_SIZE * MATRIX_SIZE];
        matrix.getValues(matrixOvSize);
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        int[] screenPixels = new int[width * height];
        bitmap.getPixels(screenPixels, 0, width, 0, 0, width, height);
        int[] finalPixelArray = screenPixels.clone();
        int r, g, b;
        int rVal, gVal, bVal;
        int pixelelId;
        int pixel;
        float matrixValue;


        for (int x = 0; x < width - MATRIX_SIZE + 1; ++x) {
            for (int y = 0; y < height - MATRIX_SIZE + 1; ++y) {
                pixelelId = (x + 1) + (y + 1) * width;
                rVal = gVal = bVal = 0;
                for (int mx = 0; mx < MATRIX_SIZE; ++mx) {
                    for (int my = 0; my < MATRIX_SIZE; ++my) {
                        pixel = screenPixels[(x + mx) + (y + my) * width];
                        matrixValue = matrixOvSize[mx + my * MATRIX_SIZE];
                        rVal += (Color.red(pixel) * matrixValue);
                        gVal += (Color.green(pixel) * matrixValue);
                        bVal += (Color.blue(pixel) * matrixValue);
                    }
                }
                r = validation((int) (rVal / factor + offset));
                g = validation((int) (gVal / factor + offset));
                b = validation((int) (bVal / factor + offset));
                finalPixelArray[pixelelId] = Color.argb(Color.alpha(screenPixels[pixelelId]), r, g, b);
            }
        }
        return Bitmap.createBitmap(finalPixelArray, width, height, bitmap.getConfig());
    }
}