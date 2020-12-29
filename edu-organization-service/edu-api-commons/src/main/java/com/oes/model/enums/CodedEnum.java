package com.oes.model.enums;

import java.util.Arrays;
import java.util.Optional;

public interface CodedEnum {
    int getCode();

    static <E extends Enum<?> & CodedEnum> Optional<E> codeOf(Class<E> enumClass, int code) {
        return Arrays.stream(enumClass.getEnumConstants()).filter(e -> e.getCode() == code).findAny();
    }
}
