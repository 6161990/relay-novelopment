package com.yoon.relayNovelopment.domain;

import lombok.EqualsAndHashCode;
import lombok.Value;

import java.util.Objects;

import static org.valid4j.Validation.validate;

@EqualsAndHashCode(callSuper = false)
@Value(staticConstructor = "of")
public class RelayArticleKey extends ArticleKey {
    String key;

    private RelayArticleKey(String key) {
        validate(Objects.nonNull(key), new NovelBoardException("RelayNovelKey Value is Null."));
        this.key = key;
    }

}
