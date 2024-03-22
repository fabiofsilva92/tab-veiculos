package com.itau.desafio.domain.enums;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.itau.desafio.framework.util.deserializers.CaseInsensitiveEnumDeserializer;

@JsonDeserialize(using = CaseInsensitiveEnumDeserializer.class)
public enum TiposVeiculoEnum {
    CARRO,
    MOTO,
    ONIBUS,
    CAMINHAO
}
