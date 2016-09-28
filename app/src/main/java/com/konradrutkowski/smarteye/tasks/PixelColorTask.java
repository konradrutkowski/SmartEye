package com.konradrutkowski.smarteye.tasks;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.widget.ImageView;

import com.konradrutkowski.smarteye.instructions.PictureOperation;

import java.lang.ref.WeakReference;

/**
 * Created by Xooq on 2015-01-13.
 */
public class PixelColorTask extends AsyncTask<PictureOperation, PictureOperation, Bitmap> {
    public final WeakReference<Bitmap> bmpRef;
    private final WeakReference<ImageView> imageViewReference;
    ProgressDialog dialog;
    PictureOperation pictureOperation;


    public PixelColorTask(PictureOperation pictureOperation, Bitmap bmp, ImageView imageView, Context ctx) {
        bmpRef = new WeakReference<>(bmp);
        imageViewReference = new WeakReference<>(imageView);

        dialog = new ProgressDialog(ctx);
        dialog.setCancelable(false);
        dialog.setMessage("Modyfikuje obraz");
        this.pictureOperation = pictureOperation;


    }


    // start async task
    protected void onPreExecute() {
        dialog.show();
    }

    @Override
    protected Bitmap doInBackground(PictureOperation... params) {
        dialog.show();

        Bitmap bmp = bmpRef.get();
        if (bmp == null) {
            return null;
        }
        pictureOperation.bitmapOperation(bmp);
        if (pictureOperation.getProcessedBitmap() == null) {
            return null;
        }

        Bitmap.createBitmap(pictureOperation.getProcessedBitmap());
        return pictureOperation.getProcessedBitmap();
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
