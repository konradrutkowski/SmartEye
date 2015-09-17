package com.konradrutkowski.smarteye;

import android.app.ActionBar;
import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.SeekBar;

public class BrigthnessActivity extends BaseActivity {

    private SeekBar seekBar;
    ImageView image;
    Bitmap bmp;
    int tmp;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seekbar);
        seekBar = (SeekBar) findViewById(R.id.seekBar);
        ActionBar actionBar = getActionBar();
        actionBar.setBackgroundDrawable((ColorDrawable) getResources().getDrawable(R.drawable.bars));
        actionBar.setDisplayOptions(ActionBar.DISPLAY_HOME_AS_UP
                | ActionBar.DISPLAY_SHOW_TITLE | ActionBar.DISPLAY_SHOW_CUSTOM);
        image = (ImageView) findViewById(R.id.imageView);
        //String urlPhoto = Environment.getExternalStorageDirectory().getPath() + "/test3.jpg";
        bmp = LoadImage.getBitmap(PhotoURI.getURI());
        image.setImageBitmap(bmp);

        seekBar.setMax(100);
        seekBar.setProgress(50);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {


            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                // TODO Auto-generated method stub
                //PixelColorTask task;
                int prg = seekBar.getProgress();

                if (prg != tmp) {
                    tmp = prg;
                    Log.d("HEHE", "PRG "+prg+"     prg "+prg);
                    //ColorUpTask t1 = new ColorUpTask(operationType,1.0f);
                    float value = (prg-50)/100.0f;
                    Log.d("HEHE","FLOAT HEHE "+value+ "PRG "+prg+"     prg "+prg);
                    ColorUp t1 = new ColorUp(0,value);//prg-50/1000);
                    PixelColorTask taskPixel = new PixelColorTask(t1, bmp, image, BrigthnessActivity.this);
                    taskPixel.execute();
                }
            }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {
            // TODO Auto-generated method stub

        }

        @Override
        public void onProgressChanged(SeekBar seekBar, int progress,
        boolean fromUser) {


        }
    });
    }


    @Override
    protected ImageView getImage() {
        return image;
    }
}
