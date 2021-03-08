package com.chengfu.android.emoji;

import androidx.annotation.IntegerRes;

public class Emoji {
    String code;
    @IntegerRes
    int res;
    String name;

    public Emoji() {
    }

    public Emoji(String code, int res) {
        this.code = code;
        this.res = res;
    }

    public Emoji(String code, int res, String name) {
        this.code = code;
        this.res = res;
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getRes() {
        return res;
    }

    public void setRes(int res) {
        this.res = res;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Emoji{" +
                "code='" + code + '\'' +
                ", res=" + res +
                ", name='" + name + '\'' +
                '}';
    }
}
