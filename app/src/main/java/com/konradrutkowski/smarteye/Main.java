package com.konradrutkowski.smarteye;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;


public class Main extends Activity {
    Bitmap bmp;
    static ImageView image;
    private MenuItem menuItem;
    Boolean firstTime = true;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        if(firstTime) {
            //PhotoURI.setURI(Environment.getExternalStorageDirectory().getPath() + "/test3.jpg");
            firstTime = false;
        }
        setContentView(R.layout.activity_main);
       // PhotoURI.URI = Environment.getExternalStorageDirectory().getPath() + "/test3.jpg";
        ActionBar actionBar = getActionBar();
        actionBar.setBackgroundDrawable((ColorDrawable)getResources().getDrawable(R.drawable.bars));
        actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_HOME
                | ActionBar.DISPLAY_SHOW_TITLE | ActionBar.DISPLAY_SHOW_CUSTOM);

        image = (ImageView) findViewById(R.id.imageView);
       // String urlPhoto = Environment.getExternalStorageDirectory().getPath() + "/test3.jpg";
      //  bmp = LoadImage.getBitmap(PhotoURI.URI);
      //  image.setImageBitmap(bmp);

    }

    @Override
    protected void onPause() {
        super.onPause();
        bmp = null;
    }

    @Override
    protected void onStart(){
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
    public void Sharp(View view){
        Intent intent = new Intent(this, SharpeningActivity.class);
        startActivity(intent);

    }
    public void ColorUp(View view){
        Intent intent = new Intent(this, ColorUpActivity.class);
        startActivity(intent);
    }
    public void Brightness(View view){
        Intent intent = new Intent(this, BrigthnessActivity.class);
        startActivity(intent);

    }
    public void GammaCorrection(View view){
        Intent intent = new Intent(this, GammaCorrectionActivity.class);
        startActivity(intent);

    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_save:
                menuItem = item;
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