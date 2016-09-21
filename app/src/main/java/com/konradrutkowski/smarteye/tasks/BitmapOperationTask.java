package com.konradrutkowski.smarteye.tasks;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.os.AsyncTask;
import android.widget.ImageView;

import com.konradrutkowski.smarteye.instructions.ConvolutionMatrix;

import java.lang.ref.WeakReference;

/**
 * Created by Xooq on 2015-01-12.
 */
public class BitmapOperationTask extends AsyncTask<Bitmap, ImageView, Bitmap> {
    public final WeakReference<Bitmap> bmpRef;
    private final WeakReference<ImageView> imageViewReference;
    ProgressDialog dialog;
    float[] mat;
    int factor;
    int offset;

    public BitmapOperationTask(float[] mat, int factor, int offset, Bitmap bmp, ImageView imageView, Context ctx) {
        bmpRef = new WeakReference<>(bmp);
        imageViewReference = new WeakReference<>(imageView);
        dialog = new ProgressDialog(ctx);
        dialog.setCancelable(false);
        dialog.setMessage("Modyfikuje obraz");
        this.mat = mat;
        this.factor = factor;
        this.offset = offset;
    }

    protected void onPreExecute() {
        dialog.show();
    }

    @Override
    protected Bitmap doInBackground(Bitmap... params) {
        dialog.show();
        Bitmap bmp = bmpRef.get();
        Matrix matrix = new Matrix();
        matrix.setValues(mat);
        return (ConvolutionMatrix.convolute(bmp, matrix, factor, offset));

    }

    @Override
    protected void onPostExecute(Bitmap bitmap) {
        if (imageViewReference != null && bitmap != null) {
            final ImageView imageView = imageViewReference.get();
            if (imageView != null) {
                imageView.setImageBitmap(bitmap);
            }
        }
        dialog.dismiss();
    }
}
