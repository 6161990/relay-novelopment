package com.yoon.relayNovelopment.domain;

import lombok.EqualsAndHashCode;
import lombok.Value;

import java.util.Objects;

import static org.valid4j.Validation.validate;

@EqualsAndHashCode(callSuper = false)
@Value(staticConstructor = "of")
public class OpeningKey extends ArticleKey {
    String key;

    private OpeningKey(String key) {
        validate(Objects.nonNull(key), new NovelBoardException("OpeningKey Value is Null."));
        this.key = key;
    }

}
