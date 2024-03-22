package com.itau.desafio.framework.util.validators;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;


@Target({ FIELD })
@Retention(RUNTIME)
@Constraint(validatedBy = MarcaValidator.class)
public @interface MarcaValida {
    String message() default "Marca inválida";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}