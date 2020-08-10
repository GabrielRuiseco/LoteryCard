package com.example.myapplication.clases;

import android.media.Image;

public class LoteryCard {

    private int number, picture;

    public LoteryCard(int number, int picture) {

        this.number = number;
        this.picture = picture;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getPicture() {
        return picture;
    }

    public void setPicture(int picture) {
        this.picture = picture;
    }
}
