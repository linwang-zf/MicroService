package com.oes.model.enums;

public enum RegistState implements CodedEnum {
    unChecked(0, "机构尚未审核"),
    CheckedPass(1, "机构审核通过"),
    CheckedFailed(2, "机构审核失败"),
    writtenOff(3, "机构已被注销");

    private static final RegistState[] ENUMS = RegistState.values();
    private Integer value;
    private String description;

    RegistState(Integer value, String description){
        this.value = value;
    }

    public static RegistState getTypeByInt(int i) {
        if (i>=0 && i <= 3) {
            return ENUMS[i];
        } else {
            return ENUMS[0];
        }
    }

    public int getValue() {
        return ordinal();
    }
    public String getDescription() {return description;}

    @Override
    public int getCode() {
        return value;
    }
}
