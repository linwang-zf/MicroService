package com.oes.model.enums;

public enum PictureType implements CodedEnum {
    png(0), jpg(1), tmp(2), gif(3), tif(4);

    int value;

    PictureType(int value) {
        this.value = value;
    }

    public static PictureType getTypeByInt(int i) {
        switch (i) {
            case 0:
                return png;
            case 1:
                return jpg;
            case 2:
                return tmp;
            case 3:
                return gif;
            case 4:
                return tif;
            default:
                return png;
        }
    }

    public int getValue() {
        return value;
    }


    @Override
    public int getCode() {
        return value;
    }
}
