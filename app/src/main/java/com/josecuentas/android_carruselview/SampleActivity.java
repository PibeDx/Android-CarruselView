package com.josecuentas.android_carruselview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.josecuentas.android_carruselview.widget.TabItem;
import com.josecuentas.android_carruselview.widget.TabLayoutCarrusel;

import java.util.ArrayList;
import java.util.List;

public class SampleActivity extends AppCompatActivity implements TabLayoutCarrusel.Listener {

    private TabLayoutCarrusel mCustomTabLayout;

    List<Item> mOrdenItemList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sample);
        mCustomTabLayout = (TabLayoutCarrusel) findViewById(R.id.ctlaContainer);

        mOrdenItemList.add(new Item(R.drawable.ic_entretainment,"ic_entretainment"));
        mOrdenItemList.add(new Item(R.drawable.ic_hotels,"ic_hotels"));
        mOrdenItemList.add(new Item(R.drawable.ic_restaurants,"ic_restaurants"));
        mOrdenItemList.add(new Item(R.drawable.ic_coupons,"ic_coupons"));
        mOrdenItemList.add(new Item(R.drawable.ic_promos,"ic_promos"));


        mCustomTabLayout.setOrdenItemList(mOrdenItemList);
        mCustomTabLayout.post(new Runnable() {
            @Override public void run() {
                mCustomTabLayout.setupTab(mOrdenItemList.size());
                mCustomTabLayout.centerTab(0);
            }
        });
        //mCustomTabLayout.setItemCenter(1);
        mCustomTabLayout.setListener(this);

    }

    @Override public void onTabSelected(TabItem item) {
        Item tabItem = (Item) item.get();
        Toast.makeText(this, tabItem.name, Toast.LENGTH_SHORT).show();
    }
}
