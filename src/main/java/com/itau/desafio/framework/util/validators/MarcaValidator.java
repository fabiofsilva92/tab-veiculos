package com.itau.desafio.framework.util.validators;


import com.itau.desafio.framework.exceptions.VeiculosException;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Arrays;
import java.util.List;

public class MarcaValidator implements ConstraintValidator<MarcaValida, String> {
    //TODO apenas para exemplos as marcas abaixo, poderia usar um Enum também.
    List<String> marcasPermitidas = Arrays.asList("Audi", "BMW", "Chevrolet", "Ford", "Honda", "Toyota"); // Exemplo de lista de marcas permitidas

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        if(!marcasPermitidas.contains(s)){
            throw new VeiculosException("Marca inválida");
        }
        return true;
    }

}
