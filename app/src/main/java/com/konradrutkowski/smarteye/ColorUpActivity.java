package com.konradrutkowski.smarteye;

import android.app.ActionBar;
import android.app.Activity;
import android.content.res.ColorStateList;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;

public class ColorUpActivity extends BaseActivity {
    private SeekBar seekBar;
    ImageView image;
    Bitmap bmp;
    int tmp;
    RadioGroup rgb;
    RadioButton r;
    RadioButton g;
    RadioButton b;
    int operationType = 1;
    public MenuItem menuItem;
    int typeProgress1 = 50;
    int typeProgress2 = 50;
    int typeProgress3 = 50;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_colorrgb);
        seekBar = (SeekBar) findViewById(R.id.seekBar);
        //seekBar.tint
        // seekBar.getBackgroundTintMode();
        //seekBar.tint
        if (Build.VERSION.SDK_INT >= 21) {
            seekBar.setBackgroundTintList(ColorStateList.valueOf(Color.TRANSPARENT));
        }
        ActionBar actionBar = getActionBar();
        actionBar.setBackgroundDrawable((ColorDrawable) getResources().getDrawable(R.drawable.bars));
        actionBar.setDisplayOptions(ActionBar.DISPLAY_HOME_AS_UP
                | ActionBar.DISPLAY_SHOW_TITLE | ActionBar.DISPLAY_SHOW_CUSTOM);
        image = (ImageView) findViewById(R.id.imageView);
        //String urlPhoto = Environment.getExternalStorageDirectory().getPath() + "/test3.jpg";
        bmp = LoadImage.getBitmap(PhotoURI.URI);
        image.setImageBitmap(bmp);
        tmp = 0;
        rgb = (RadioGroup) findViewById(R.id.rgbgroup);
        r = (RadioButton) findViewById(R.id.r);
        g = (RadioButton) findViewById(R.id.g);
        b = (RadioButton) findViewById(R.id.b);


        rgb.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == (R.id.r)) {
                    Log.d("ColorRGB: ", "1");
                    operationType = 1;
                    seekBar.setProgress(typeProgress1);
                }
                if (checkedId == (R.id.g)) {
                    Log.d("ColorRGB: ", "2");
                    operationType = 2;
                    seekBar.setProgress(typeProgress2);
                }
                if (checkedId == (R.id.b)) {
                    Log.d("ColorRGB: ", "3");
                    operationType = 3;
                    seekBar.setProgress(typeProgress3);

                }
                //operationType = checkedId;

            }
        });


        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {


            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                // TODO Auto-generated method stub
                //PixelColorTask task;
                int prg = seekBar.getProgress();
                if (operationType == 1) {
                    typeProgress1 = prg;
                    Log.d("PROGRESSX", " 1 " + prg);

                } else if (operationType == 2) {
                    typeProgress2 = prg;
                    Log.d("PROGRESSX", " 2 " + prg);
                } else if (operationType == 3) {
                    typeProgress3 = prg;
                    Log.d("PROGRESSX", " 3 " + prg);
                }

                if (prg != tmp) {
                    tmp = prg;
                    Log.d("HEHE", "PRG " + prg + "  OPTYPE " + operationType + "   prg " + prg);
                    //ColorUpTask t1 = new ColorUpTask(operationType,1.0f);
                    float hehe = (prg - 50) / 100.0f;
                    Log.d("HEHE", "FLOAT HEHE " + hehe + "PRG " + prg + "  OPTYPE " + operationType + "   prg " + prg);
                    ColorUp t1 = new ColorUp(operationType, hehe);//prg-50/1000);
                    PixelColorTask taskPixel = new PixelColorTask(t1, bmp, image, ColorUpActivity.this);
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
    public ImageView getImage(){
        return image;
    }

    }
