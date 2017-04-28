package com.josecuentas.android_carruselview.widget;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.design.widget.TabLayout;
import android.support.v4.content.ContextCompat;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.util.AttributeSet;

import com.josecuentas.android_carruselview.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by jcuentas on 27/04/17.
 */

public class TabLayoutCarrusel extends TabLayout implements TabLayout.OnTabSelectedListener {

    private List<TabItem> mOrdenItemList = new ArrayList<>();
    private List<TabItem> mItemTempList = new ArrayList<>();
    private Listener mListener;
    private int mSizeMaxTab = 5;

    public TabLayoutCarrusel(Context context) {
        super(context);
        init();
    }

    public TabLayoutCarrusel(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public TabLayoutCarrusel(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public void setListener(Listener listener) {
        mListener = listener;
        addOnTabSelectedListener(this);
    }

    public void init() {
        setSelectedTabIndicatorHeight(0);
    }

    public void setOrdenItemList(List<? extends TabItem> ordenItemList) {
        mOrdenItemList = (List<TabItem>) ordenItemList;
    }

    public void setOrdenItemList(List<? extends TabItem> ordenItemList, int positionSelect) {
        mOrdenItemList = (List<TabItem>) ordenItemList;
        centerTab(positionSelect);
    }

    public void centerTab(int position) {
        int midSizeMaxTab = mSizeMaxTab / 2 + 1;
        Collections.rotate(mOrdenItemList, mSizeMaxTab - position - midSizeMaxTab);
        for (int i = 0; i <getTabCount() ; i++) {
            if (i == (getTabCount() / 2)) {
                Drawable drawable = DrawableCompat.wrap(ContextCompat.getDrawable(getContext(), mOrdenItemList.get(i).getIcon()));
                int color = ContextCompat.getColor(getContext(), R.color.colorAccent);
                DrawableCompat.setTint(drawable.mutate(), color);
                /*if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) DrawableCompat.setTint(drawable, color);
                else drawable.mutate().setColorFilter(color, PorterDuff.Mode.SRC_IN);*/
                getTabAt(i).setIcon(drawable).setTag(mOrdenItemList.get(i));
            } else {
                Drawable drawable = DrawableCompat.wrap(ContextCompat.getDrawable(getContext(), mOrdenItemList.get(i).getIcon()));
                getTabAt(i).setIcon(drawable).setTag(mOrdenItemList.get(i));
                //new BitmapDrawable();
            }
        }
    }

    public void setupTab() {
        for (int i = 0; i < mSizeMaxTab; i++) {
            TabLayout.Tab tab = newTab().setIcon(mOrdenItemList.get(i).getIcon());
            addTab(tab);
        }
    }

    @Override public void onTabSelected(Tab tab) {
        if (getTabCount() != 1) {
            int position = tab.getPosition();
            if (mListener != null) {
                TabItem item = (TabItem) tab.getTag();
                mListener.onTabSelected(item);
                centerTab(position);
            }
        }
    }

    @Override public void onTabUnselected(Tab tab) {

    }



    @Override public void onTabReselected(Tab tab) {
        int position = tab.getPosition();
        if (mListener != null) {
            TabItem item = (TabItem) tab.getTag();
            mListener.onTabSelected(item);
            centerTab(position);
        }
    }

    public void setSizeMaxTab(int sizeMaxTab) {
        mSizeMaxTab = sizeMaxTab;
    }

    public interface Listener {
        void onTabSelected(TabItem item);
    }
}
