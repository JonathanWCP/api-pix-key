package com.pix.api.dto.validation.annotation;

import com.pix.api.dto.validation.KeyTypeValidation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = KeyTypeValidation.class)
public @interface KeyType {
    String message() default "O valor não é permitido";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

    String[] value();
}
