package com.josecuentas.android_carruselview;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.content.ContextCompat;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.josecuentas.android_carruselview.widget.TabLayoutCarrusel;
import com.josecuentas.android_carruselview.widget.TabItem;

import java.util.ArrayList;
import java.util.List;

/*
* info drawable tint http://stackoverflow.com/a/37434219
* */

public class MainActivity extends AppCompatActivity implements TabLayoutCarrusel.Listener{

    private static final String TAG = "MainActivity";
    private TabLayout mTabLayout;
    private TabLayoutCarrusel mCustomTabLayout;
    private String[] mTitles = {"Entretenimiento", "Hoteles", "Restaurantes", "Cupones", "Promociones", };
    private int[] mIcons = {R.drawable.ic_entretainment, R.drawable.ic_hotels, R.drawable.ic_restaurants, R.drawable.ic_coupons, R.drawable.ic_promos};
    List<Item> mOrdenItemList = new ArrayList<>();
    List<Item> mItemTempList = new ArrayList<>();

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTabLayout = (TabLayout) findViewById(R.id.tlaContainer);
        mCustomTabLayout = (TabLayoutCarrusel) findViewById(R.id.ctlaContainer);

        mOrdenItemList.add(new Item(R.drawable.ic_entretainment,"ic_entretainment"));
        mOrdenItemList.add(new Item(R.drawable.ic_hotels,"ic_hotels"));
        mOrdenItemList.add(new Item(R.drawable.ic_restaurants,"ic_restaurants"));
        mOrdenItemList.add(new Item(R.drawable.ic_coupons,"ic_coupons"));
        mOrdenItemList.add(new Item(R.drawable.ic_promos,"ic_promos"));
        //mOrdenItemList.add(new Item(R.drawable.ic_promos));

        mTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override public void onTabSelected(TabLayout.Tab tab) {
                int position = tab.getPosition();

                Log.i(TAG, "onTabSelected: position " + position);
                centerArray(position);
                Item tag = (Item) tab.getTag();
                Toast.makeText(MainActivity.this, tag.name, Toast.LENGTH_SHORT).show();
            }

            @Override public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override public void onTabReselected(TabLayout.Tab tab) {
                int position = tab.getPosition();
                Log.i(TAG, "onTabReselected: position " + position);
                centerArray(position);
                Item tag = (Item) tab.getTag();
                Toast.makeText(MainActivity.this, tag.name, Toast.LENGTH_SHORT).show();
            }
        });

        mTabLayout.setEnabled(false);

        mTabLayout.post(new Runnable() {
            @Override public void run() {
                //mTabLayout.setupWithViewPager(mVpaContainer);
                Log.d(TAG, "run() called");
                setupTab(mOrdenItemList.size());
                centerArray(1);
            }
        });

        mCustomTabLayout.setOrdenItemList(mOrdenItemList, 4);
        mCustomTabLayout.setListener(this);
    }



    public void centerArray(int position) {
        mItemTempList.clear();
        int size = mOrdenItemList.size();
        final int midSize = (size / 2) + 1;

        if ((position + 1) > midSize) {
            for (int i = position-(size/2); i < size; i++) {
                mItemTempList.add(mOrdenItemList.get(i));
            }
            for (int i = 0; i < position-(size/2); i++) {
                mItemTempList.add(mOrdenItemList.get(i));
            }
            //[0][1*][2][3][4] position
            //[1][2][3][4][5] values

            //[4][0][1][2][3] position
            //[5][1][2][3][4] values
        } else if ((position + 1) < midSize) {
            for (int i = position + midSize; i < size; i++) {
                mItemTempList.add(mOrdenItemList.get(i));
            }
            for (int i = 0; i < position + midSize; i++) {
                mItemTempList.add(mOrdenItemList.get(i));
            }
        } else {
            for (int i = 0; i < size; i++) {
                mItemTempList.add(mOrdenItemList.get(i));
            }
        }

        mOrdenItemList = new ArrayList<>(mItemTempList);

        for (int i = 0; i <mTabLayout.getTabCount() ; i++) {
            if (i == (size / 2)) {
                Drawable drawable = DrawableCompat.wrap(ContextCompat.getDrawable(this, mItemTempList.get(i).resourceImage));
                int color = ContextCompat.getColor(this, R.color.colorAccent);
                DrawableCompat.setTint(drawable.mutate(), color);
                /*if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) DrawableCompat.setTint(drawable, color);
                else drawable.mutate().setColorFilter(color, PorterDuff.Mode.SRC_IN);*/
                mTabLayout.getTabAt(i).setIcon(drawable).setTag(mItemTempList.get(i));
            } else {
                Drawable drawable = DrawableCompat.wrap(ContextCompat.getDrawable(this, mItemTempList.get(i).resourceImage));
                mTabLayout.getTabAt(i).setIcon(drawable).setTag(mItemTempList.get(i));
                //new BitmapDrawable();
            }
        }
    }

    private void setupTab(int size) {
        for (int i = 0; i < size; i++) {
            TabLayout.Tab tab = mTabLayout.newTab().setIcon(mOrdenItemList.get(i).resourceImage);
            mTabLayout.addTab(tab);
        }
    }

    @Override public void onTabSelected(TabItem item) {
        Item tabItem = (Item) item.get();
        Toast.makeText(this, tabItem.name, Toast.LENGTH_SHORT).show();
    }
}