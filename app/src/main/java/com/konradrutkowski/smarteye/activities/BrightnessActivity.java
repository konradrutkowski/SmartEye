package com.konradrutkowski.smarteye.activities;

import android.app.ActionBar;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.SeekBar;

import com.konradrutkowski.smarteye.R;
import com.konradrutkowski.smarteye.file.PhotoURI;
import com.konradrutkowski.smarteye.file.load.LoadImage;
import com.konradrutkowski.smarteye.instructions.ColorUp;
import com.konradrutkowski.smarteye.tasks.PixelColorTask;

public class BrightnessActivity extends BaseActivity {

    ImageView image;
    Bitmap bmp;
    int tmp;
    private SeekBar seekBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seekbar);
        seekBar = (SeekBar) findViewById(R.id.seekBar);
        ActionBar actionBar = getActionBar();
        if (actionBar != null) {
            actionBar.setBackgroundDrawable(getResources().getDrawable(R.drawable.bars));

            actionBar.setDisplayOptions(ActionBar.DISPLAY_HOME_AS_UP
                    | ActionBar.DISPLAY_SHOW_TITLE | ActionBar.DISPLAY_SHOW_CUSTOM);
        }
        image = (ImageView) findViewById(R.id.imageView);
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
                    
                    //ColorUpTask t1 = new ColorUpTask(operationType,1.0f);
                    float value = (prg - 50) / 100.0f;
                    
                    ColorUp t1 = new ColorUp(0, value);//prg-50/1000);
                    PixelColorTask taskPixel = new PixelColorTask(t1, bmp, image, BrightnessActivity.this);
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
