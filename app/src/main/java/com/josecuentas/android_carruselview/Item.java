package com.josecuentas.android_carruselview;

import com.josecuentas.android_carruselview.widget.TabItem;

/**
 * Created by jcuentas on 27/04/17.
 */

public class Item implements TabItem {

    public int resourceImage;
    public String name;

    public Item(int resourceImage) {
        this.resourceImage = resourceImage;
    }

    public Item(int resourceImage, String name) {
        this.resourceImage = resourceImage;
        this.name = name;
    }

    @Override public Object get() {
        return this;
    }

    @Override public int getIcon() {
        return resourceImage;
    }
}
