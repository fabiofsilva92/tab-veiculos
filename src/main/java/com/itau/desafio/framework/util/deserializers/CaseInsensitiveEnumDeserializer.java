package com.itau.desafio.framework.util.deserializers;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.itau.desafio.domain.enums.TiposVeiculoEnum;

import java.io.IOException;

public class CaseInsensitiveEnumDeserializer extends JsonDeserializer<TiposVeiculoEnum> {

    @Override
    public TiposVeiculoEnum deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        String value = jsonParser.getText().toUpperCase();
        return TiposVeiculoEnum.valueOf(value);
    }
}