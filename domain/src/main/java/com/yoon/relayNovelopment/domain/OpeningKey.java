package com.yoon.relayNovelopment.domain;

import lombok.Value;

import java.util.Objects;

import static org.valid4j.Validation.validate;

@Value(staticConstructor = "of")
public class OpeningKey implements NovelKey {
    String value;

    private OpeningKey(String value) {
        validate(Objects.nonNull(value), new NovelBoardException("OpeningKey Value is Null."));
        this.value = value;
    }

}
