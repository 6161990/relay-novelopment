package com.yoon.relayNovelopment.domain;

import lombok.Value;

import java.util.Objects;

import static org.valid4j.Validation.validate;

@Value(staticConstructor = "of")
public class OpeningKey extends NovelKey {
    String key;

    private OpeningKey(String key) {
        validate(Objects.nonNull(key), new NovelBoardException("OpeningKey Value is Null."));
        this.key = key;
    }

}
