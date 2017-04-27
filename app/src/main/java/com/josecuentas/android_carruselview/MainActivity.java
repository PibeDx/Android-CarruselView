package com.josecuentas.android_carruselview;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private TabLayout mTabLayout;
    private String[] mTitles = {"Entretenimiento", "Hoteles", "Restaurantes", "Cupones", "Promociones", };
    private int[] mIcons = {R.drawable.ic_entretainment, R.drawable.ic_hotels, R.drawable.ic_restaurants, R.drawable.ic_coupons, R.drawable.ic_promos};
    List<Item> mOrdenItemList = new ArrayList<>();
    List<Item> mItemTempList = new ArrayList<>();

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTabLayout = (TabLayout) findViewById(R.id.tlaContainer);



        mOrdenItemList.add(new Item(R.drawable.ic_entretainment));
        mOrdenItemList.add(new Item(R.drawable.ic_hotels));
        mOrdenItemList.add(new Item(R.drawable.ic_restaurants));
        mOrdenItemList.add(new Item(R.drawable.ic_coupons));
        mOrdenItemList.add(new Item(R.drawable.ic_promos));
        //mOrdenItemList.add(new Item(R.drawable.ic_promos));


        mTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override public void onTabSelected(TabLayout.Tab tab) {
                int position = tab.getPosition();

                Log.i(TAG, "onTabSelected: position " + position);
                centerArray(position);
            }

            @Override public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override public void onTabReselected(TabLayout.Tab tab) {
                int position = tab.getPosition();
                Log.i(TAG, "onTabReselected: position " + position);
                centerArray(position);
            }
        });

        mTabLayout.post(new Runnable() {
            @Override public void run() {
                //mTabLayout.setupWithViewPager(mVpaContainer);
                Log.d(TAG, "run() called");
                setupTab(mOrdenItemList.size());
            }
        });
    }



    public void centerArray(int position) {
        mItemTempList.clear();
        int size = mOrdenItemList.size();
        final int midSize = (size / 2) + 1;


        //4,5

        if ((position + 1) > midSize) {
            /*for (int i = (size-(position + 1)); i < size; i++) {
                itemTempList.add(mOrdenItemList.get(i));
            }
            for (int i = 0; i < (size-(position + 1)); i++) {
                itemTempList.add(mOrdenItemList.get(i));
            }*/

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


        /*for (int i = midSize; i < size; i++) {
            itemTempList.add(mOrdenItemList.get(i));
        }


        //1,2,3
        for (int i = position; i < size; i++) {
            itemTempList.add(mOrdenItemList.get(i));
        }*/

        for (int i = 0; i <mTabLayout.getTabCount() ; i++) {
            if (i == midSize) {
                mTabLayout.getTabAt(i).setIcon(mItemTempList.get(i).resourceImage);
            } else {
                mTabLayout.getTabAt(i).setIcon(mItemTempList.get(i).resourceImage);
            }
        }
    }


    /*public void centerArray(int position) {
        int size = mOrdenItemList.size();
        List<Item> itemTempList = new ArrayList<>();
        int midSize = (size / 2) + 1;



        for (int i = midSize; i < size; i++) {
            itemTempList.add(mOrdenItemList.get(i));
        }

        for (int i = 0; i < midSize; i++) {
            itemTempList.add(mOrdenItemList.get(i));
        }

        for (int i = 0; i <mTabLayout.getTabCount() ; i++) {
            mTabLayout.getTabAt(i).setIcon(itemTempList.get(i).resourceImage);
        }

    }*/

    private void setupTab(int size) {
        for (int i = 0; i < size; i++) {
            TabLayout.Tab tab = mTabLayout.newTab().setIcon(mOrdenItemList.get(i).resourceImage);
            mTabLayout.addTab(tab);
        }
    }
}
