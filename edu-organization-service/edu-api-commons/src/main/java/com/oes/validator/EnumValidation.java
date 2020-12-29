package com.oes.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * To validate a enum's value is right or not .
 * <p>
 * example one, if a enum like
 * <code>
 * public enum Sex {
 * <p>
 * MALE, FEMALE, OTHER
 * }
 * </code>
 * can use the annotation as <code>@Enum(clazz = Sex.class)</code>, and param sex = 0;
 * Or use the annotation as <code>@Enum(clazz = Sex.class, method = "name")</code>,
 * and param sex = "MALE";
 * <p>
 * example two, if a enum like
 * <code>
 * public enum Role {
 * <p>
 * ADMIN(1, "ADMIN"),
 * TEST(2, "TEST"),
 * DEVELOP(3, "DEVELOP");
 * <p>
 * private int value;
 * private String desc;
 * <p>
 * Role(int value, String desc) {
 * this.value = value;
 * this.desc = desc;
 * }
 * <p>
 * public int getValue() {
 * return value;
 * }
 * <p>
 * public void setValue(int value) {
 * this.value = value;
 * }
 * <p>
 * public String getDesc() {
 * return desc;
 * }
 * <p>
 * public void setDesc(String desc) {
 * this.desc = desc;
 * }
 * }
 * </code>
 * can use the annotation as <code>@Enum(clazz = Role.class, method = "getValue")</code>,
 * and param role = 1; Or use the annotation as <code>@Enum(clazz = Role.class,
 * method = "getDesc")</code>,and param role = "ADMIN";
 *
 * @ClassName EnumValidate.java
 * @Description Controller入参对象中属性枚举项校验
 */
@Documented
@Target({METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE})
@Retention(RUNTIME)
@Repeatable(EnumValidation.List.class)
@Constraint(validatedBy = {EnumValidator.class})
public @interface EnumValidation {
    String message() default "{*.validation.constraint.Enum.message}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    /**
     * the enum's class-type
     *
     * @return Class
     */
    Class<?> clazz();

    /**
     * the method's name ,which used to validate the enum's value
     *
     * @return method's name
     */
    String method() default "ordinal";

    /**
     * Defines several {@link EnumValidation} annotations on the same element.
     *
     * @see EnumValidation
     */
    @Documented
    @Target({METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE})
    @Retention(RUNTIME)
    @interface List {
        EnumValidation[] value();
    }
}
