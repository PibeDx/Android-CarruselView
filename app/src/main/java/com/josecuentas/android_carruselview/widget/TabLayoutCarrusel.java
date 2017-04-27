package com.josecuentas.android_carruselview.widget;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.design.widget.TabLayout;
import android.support.v4.content.ContextCompat;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.util.AttributeSet;

import com.josecuentas.android_carruselview.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jcuentas on 27/04/17.
 */

public class TabLayoutCarrusel extends TabLayout implements TabLayout.OnTabSelectedListener {

    private List<TabItem> mOrdenItemList = new ArrayList<>();
    private List<TabItem> mItemTempList = new ArrayList<>();
    private Listener mListener;

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

        for (int i = 0; i <getTabCount() ; i++) {
            if (i == (size / 2)) {
                Drawable drawable = DrawableCompat.wrap(ContextCompat.getDrawable(getContext(), mItemTempList.get(i).getIcon()));
                int color = ContextCompat.getColor(getContext(), R.color.colorAccent);
                DrawableCompat.setTint(drawable.mutate(), color);
                /*if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) DrawableCompat.setTint(drawable, color);
                else drawable.mutate().setColorFilter(color, PorterDuff.Mode.SRC_IN);*/
                getTabAt(i).setIcon(drawable).setTag(mItemTempList.get(i));
            } else {
                Drawable drawable = DrawableCompat.wrap(ContextCompat.getDrawable(getContext(), mItemTempList.get(i).getIcon()));
                getTabAt(i).setIcon(drawable).setTag(mItemTempList.get(i));
                //new BitmapDrawable();
            }
        }
    }

    public void setupTab(int size) {
        for (int i = 0; i < size; i++) {
            TabLayout.Tab tab = newTab().setIcon(mOrdenItemList.get(i).getIcon());
            addTab(tab);
        }
    }

    @Override public void onTabSelected(Tab tab) {
        if (getTabCount() != 1) {
            int position = tab.getPosition();
            if (mListener != null) {
                centerTab(position);
                TabItem item = (TabItem) tab.getTag();
                mListener.onTabSelected(item);
            }
        }
    }

    @Override public void onTabUnselected(Tab tab) {

    }



    @Override public void onTabReselected(Tab tab) {
        int position = tab.getPosition();
        if (mListener != null) {
            centerTab(position);
            TabItem item = (TabItem) tab.getTag();
            mListener.onTabSelected(item);
        }
    }

    public void setItemCenter(int itemCenter) {

    }

    public interface Listener {
        void onTabSelected(TabItem item);
    }
}
