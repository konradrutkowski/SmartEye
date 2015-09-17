package com.konradrutkowski.smarteye;

import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;

/**
 * Created by Xooq on 2015-01-20.
 */
public abstract class BaseActivity extends Activity {
    MenuItem menuItem;
    ImageView image;

    protected abstract ImageView getImage();

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.savetemp, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_savetemp:
                Log.d("ColorRGB: ", "3");
                menuItem = item;
                menuItem.expandActionView();
                Save save = new Save(getImage(), this);
                menuItem.setActionView(null);
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
