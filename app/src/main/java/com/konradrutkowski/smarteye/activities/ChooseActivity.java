package com.konradrutkowski.smarteye.activities;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.konradrutkowski.smarteye.R;
import com.konradrutkowski.smarteye.file.PhotoURI;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Xooq on 2015-01-20.
 */
public class ChooseActivity extends Activity {

    final int CAMERA_REQUEST = 100;
    final int GALLERY_REQUEST = 200;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.choose_activity);
        ActionBar actionBar = getActionBar();
        if (actionBar != null) {
            actionBar.setBackgroundDrawable(getResources().getDrawable(R.drawable.bars));

            actionBar.setDisplayOptions(
                    ActionBar.DISPLAY_SHOW_TITLE | ActionBar.DISPLAY_SHOW_CUSTOM);
        }
        //this.imageView = (ImageView) this.findViewById(R.id.imageView1);
        RelativeLayout galleryRelativeLayout = (RelativeLayout) this.findViewById(R.id.gallerybtn);

        RelativeLayout photoRelativeLayout = (RelativeLayout) this.findViewById(R.id.camerabtn);
        galleryRelativeLayout.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Wybierz zdjęcie"), GALLERY_REQUEST);
            }
        });

        photoRelativeLayout.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(cameraIntent, CAMERA_REQUEST);
            }
        });
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == GALLERY_REQUEST && resultCode == RESULT_OK) {

            Uri _uri = data.getData();


            Cursor cursor = getContentResolver().query(_uri, new String[]{android.provider.MediaStore.Images.ImageColumns.DATA}, null, null, null);
            try {
                if (cursor != null) {
                    cursor.moveToFirst();
                    final String imageFilePath = cursor.getString(0);
                    PhotoURI.setURI(imageFilePath);
                    cursor.close();
                }
            } catch (NullPointerException e) {
                PhotoURI.setURI(_uri.getEncodedPath());
            }
            Intent intent = new Intent(this, Main.class);
            startActivity(intent);
        } else if (requestCode == CAMERA_REQUEST && resultCode == RESULT_OK) {
            String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
            FileOutputStream fo;
            Bitmap bmp = (Bitmap) data.getExtras().get("data");
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            if (bmp != null) {
                bmp.compress(Bitmap.CompressFormat.PNG, 100, stream);
            }
            byte[] byteArray = stream.toByteArray();

            String uri = Environment.getExternalStorageDirectory() + "/kr" + timeStamp + ".png";
            try {
                fo = new FileOutputStream(new File(uri));
                PhotoURI.setURI(uri);
                fo.write(byteArray);
                fo.flush();
                fo.close();

            } catch (IOException e) {
                Toast.makeText(this, "Nie można zapisać", Toast.LENGTH_SHORT).show();
            }
            Intent intent = new Intent(this, Main.class);
            startActivity(intent);

        } else {
            Toast.makeText(this, "Coś poszło nie tak, spróbuj ponownie", Toast.LENGTH_SHORT).show();
        }
    }
}
