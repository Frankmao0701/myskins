package com.mykins.linkin.bean;

/**
 * Created by yjn on 2017/12/28.
 */

public enum Gender {
    MALE(1),
    FEMALE(9);

    private int value;

    Gender(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
