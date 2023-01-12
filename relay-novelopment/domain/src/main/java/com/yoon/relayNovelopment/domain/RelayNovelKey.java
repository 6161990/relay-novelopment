package com.yoon.relayNovelopment.domain;

import lombok.Value;

import java.util.Objects;

import static org.valid4j.Validation.validate;

@Value(staticConstructor = "of")
public class RelayNovelKey extends NovelKey {
    String key;

    private RelayNovelKey(String key) {
        validate(Objects.nonNull(key), new NovelBoardException("RelayNovelKey Value is Null."));
        this.key = key;
    }

}
