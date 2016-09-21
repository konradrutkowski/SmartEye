package com.konradrutkowski.smarteye.activities;

import android.app.ActionBar;
import android.graphics.Bitmap;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.SeekBar;

import com.konradrutkowski.smarteye.R;
import com.konradrutkowski.smarteye.file.PhotoURI;
import com.konradrutkowski.smarteye.file.load.LoadImage;
import com.konradrutkowski.smarteye.instructions.ColorUp;
import com.konradrutkowski.smarteye.instructions.Filter;
import com.konradrutkowski.smarteye.instructions.Sharp;
import com.konradrutkowski.smarteye.tasks.BitmapOperationTask;
import com.konradrutkowski.smarteye.tasks.PixelColorTask;

public class SharpeningActivity extends BaseActivity {
    public static final int PLEASE_WAIT_DIALOG = 1;
    static ImageView image;
    Bitmap bmp;
    int tmp;
    private SeekBar seekBar;

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

        bmp = LoadImage.getBitmap(PhotoURI.getURI());
        image.setImageBitmap(bmp);
        tmp = 0;
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {


            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                // TODO Auto-generated method stub
                BitmapOperationTask task;
                int prg = seekBar.getProgress();
                if (prg != tmp) {
                    tmp = prg;
                    switch (prg) {
                        case 0:
                            task = new BitmapOperationTask(Filter.normal, 1, 0, bmp, image, SharpeningActivity.this);
                            task.execute();
                            break;
                        case 1:
                            task = new BitmapOperationTask(Sharp.HP1, Sharp.HP1fac, 0, bmp, image, SharpeningActivity.this);
                            task.execute();
                            break;
                        case 2:
                            task = new BitmapOperationTask(Sharp.HP2, Sharp.HP2fac, 0, bmp, image, SharpeningActivity.this);
                            task.execute();
                            break;
                        case 3:
                            task = new BitmapOperationTask(Sharp.HP3, Sharp.HP3fac, 0, bmp, image, SharpeningActivity.this);
                            task.execute();
                            break;
                        case 4:
                            task = new BitmapOperationTask(Sharp.MEANREMOVAL, Sharp.MEANREMOVALfac, 0, bmp, image, SharpeningActivity.this);
                            task.execute();
                            break;
                        case 5:

                            //  task = new BitmapOperationTask(Sharp.KR1, Sharp.KR1fac, 0, bmp, image, Sharpening.this);
                            //  task.execute();
                            ColorUp t1 = new ColorUp(2, 34);
                            PixelColorTask taskPixel = new PixelColorTask(t1, bmp, image, SharpeningActivity.this);
                            taskPixel.execute(t1);
                            // taskPixel.execute(ColorUpTask.ColorUp(bmp, 2, 100));
                            break;
                        default:
                    }

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
