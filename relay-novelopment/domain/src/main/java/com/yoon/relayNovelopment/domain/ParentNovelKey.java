package com.yoon.relayNovelopment.domain;

import lombok.Value;

import java.util.Objects;

import static org.valid4j.Validation.validate;

@Value(staticConstructor = "of")
public class ParentNovelKey extends NovelKey {
    String key;

    private ParentNovelKey(String key) {
        validate(Objects.nonNull(key), new NovelBoardException("ParentNovelKey Value is Null."));
        this.key = key;
    }

}
