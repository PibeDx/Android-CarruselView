package com.josecuentas.android_carruselview;

/**
 * Created by jcuentas on 27/04/17.
 */

public class Item {


    int resourceImage;
    String name;

    public Item(int resourceImage) {
        this.resourceImage = resourceImage;
    }

    public Item(int resourceImage, String name) {
        this.resourceImage = resourceImage;
        this.name = name;
    }
}
