package com.oes.model.enums;

public enum Gender implements CodedEnum {
    male(0), female(1),unknown(2);

    int value;

    Gender(int i) {
        value = i;
    }

    public int getValue() {
        return value;
    }

    public static Gender getGenderByInt(int i){
        switch (i){
            case 0: return male;
            case 1: return female;
            default: return unknown;
        }
    }

    @Override
    public int getCode() {
        return value;
    }

}
