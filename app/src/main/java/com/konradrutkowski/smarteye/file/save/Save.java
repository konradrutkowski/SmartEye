package com.konradrutkowski.smarteye.file.save;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Environment;
import android.widget.ImageView;
import android.widget.Toast;

import com.konradrutkowski.smarteye.file.PhotoURI;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;

/**
 * Created by Xooq on 2015-01-15.
 */
public class Save {


    public Save(ImageView imageView, Context context) {
        super();
        imageView.buildDrawingCache();
        Bitmap bmp = imageView.getDrawingCache();


        File f3 = new File(Environment.getExternalStorageDirectory() + "/inpaint/");
        if (!f3.exists()) {
            f3.mkdirs();
        }
        OutputStream outStream = null;
        File file = new File(Environment.getExternalStorageDirectory() + "/inpaint/" + "seconds" + ".png");

        String path1 = Environment.getExternalStorageDirectory() + "/inpaint/" + "seconds" + ".png";
        PhotoURI.setURI(path1);
        try {
            outStream = new FileOutputStream(file);
            bmp.compress(Bitmap.CompressFormat.PNG, 85, outStream);
            outStream.flush();
            outStream.close();

            Toast.makeText(context, "Zapisano zmiany", Toast.LENGTH_LONG).show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}