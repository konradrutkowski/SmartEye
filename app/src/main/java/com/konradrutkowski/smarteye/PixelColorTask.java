package com.konradrutkowski.smarteye;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;

import java.lang.ref.WeakReference;
import java.lang.reflect.Method;
import java.util.concurrent.Callable;

/**
 * Created by Xooq on 2015-01-13.
 */
public class PixelColorTask extends AsyncTask<Pixz, Pixz, Bitmap> {
    public final WeakReference<Bitmap> bmpRef;
    private final WeakReference<ImageView> imageViewReference;
    ProgressDialog dialog;
    Pixz pixz1;




    public PixelColorTask(Pixz pixz, Bitmap bmp, ImageView imageView, Context ctx){
        bmpRef = new WeakReference<Bitmap>(bmp);
        imageViewReference = new WeakReference<ImageView>(imageView);

        dialog = new ProgressDialog(ctx);
        dialog.setCancelable(false);
        dialog.setMessage("Modyfikuje obraz");
        pixz1 = pixz;


    }



    // start async task
    protected void onPreExecute(){
        dialog.show();
    }
    @Override
    protected Bitmap doInBackground(Pixz ...params) {
        dialog.show();
        Log.d("HEHE", "Zaczynam start");
        Bitmap bmp = bmpRef.get();
        if (bmp==null){
            Log.d("HEHE", "FAKEN BEMPE TEŻ MO NULLA");
        }
        pixz1.bitmapOperation(bmp);
        if (pixz1.getBmpOut()==null){
            Log.d("HEHE", "FAKEN");
        }
       // bmp = pixz1.getBmpOut();

        bmp.createBitmap(pixz1.getBmpOut());
        return pixz1.getBmpOut();
    }

    @Override
    protected void onPostExecute(Bitmap bitmap) {
        Log.d("HEHE", "Stop");
        if (imageViewReference != null && bitmap != null) {
            final ImageView imageView = imageViewReference.get();

                Log.d("HEHE", "Wrzucam new photo1");
            if (imageView != null) {
                imageView.setImageBitmap(bitmap);
                Log.d("HEHE", "Wrzucam new photo2");
            }
        }
        dialog.dismiss();
    }
}
