package com.konradrutkowski.smarteye.activities;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.konradrutkowski.smarteye.R;
import com.konradrutkowski.smarteye.file.PhotoURI;
import com.konradrutkowski.smarteye.file.load.LoadImage;


public class Main extends Activity {
    static ImageView image;
    Bitmap bmp;
    Boolean firstTime = true;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        if (firstTime) {
            firstTime = false;
        }
        setContentView(R.layout.activity_main);
        ActionBar actionBar = getActionBar();
        actionBar.setBackgroundDrawable(getResources().getDrawable(R.drawable.bars));
        actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_HOME
                | ActionBar.DISPLAY_SHOW_TITLE | ActionBar.DISPLAY_SHOW_CUSTOM);

        image = (ImageView) findViewById(R.id.imageView);
    }

    @Override
    protected void onPause() {
        super.onPause();
        bmp = null;
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d("MAIN", "ONSTART()");
        Log.d("MAIN", PhotoURI.getURI());
        bmp = LoadImage.getBitmap(PhotoURI.getURI());
        image.setImageBitmap(bmp);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    public void Sharp(View view) {
        Intent intent = new Intent(this, SharpeningActivity.class);
        startActivity(intent);

    }

    public void ColorUp(View view) {
        Intent intent = new Intent(this, ColorUpActivity.class);
        startActivity(intent);
    }

    public void Brightness(View view) {
        Intent intent = new Intent(this, BrightnessActivity.class);
        startActivity(intent);

    }

    public void GammaCorrection(View view) {
        Intent intent = new Intent(this, GammaCorrectionActivity.class);
        startActivity(intent);

    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_save:
                MenuItem menuItem = item;
                // menuItem.setActionView(R.layout.progressbar);
                menuItem.expandActionView();

                menuItem.setActionView(null);
                break;
            default:
                break;
        }
        return true;
    }


}