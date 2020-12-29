package com.oes.model.enums;

public enum EnrollCourseStatus implements CodedEnum {
    unchecked(0), success(1), failed(2);

    private int value;

    EnrollCourseStatus(int value) {
        this.value = value;
    }

    public static EnrollCourseStatus getByInt(int i) {
        switch (i) {
            case 0:
                return unchecked;
            case 1:
                return success;
            case 2:
                return failed;
            default:
                return unchecked;
        }
    }

    @Override
    public int getCode() {
        return value;
    }
}
