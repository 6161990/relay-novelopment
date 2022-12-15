package com.yoon.relayNovelopment.domain;

import lombok.Value;

import java.util.Objects;

import static org.valid4j.Validation.validate;

@Value(staticConstructor = "of")
public class RelayNovelKey implements NovelKey {
    String key;

    private RelayNovelKey(String key) {
        this.key = key;
    }

    public String getValue() {
        validate(Objects.nonNull(key), new NovelBoardException("RelayNovelKey is Null."));
        return key;
    }
}
