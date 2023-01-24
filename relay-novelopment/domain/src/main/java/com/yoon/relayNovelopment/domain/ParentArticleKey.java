package com.yoon.relayNovelopment.domain;

import lombok.Value;

import java.util.Objects;

import static org.valid4j.Validation.validate;

@Value(staticConstructor = "of")
public class ParentArticleKey extends ArticleKey {
    String key;

    private ParentArticleKey(String key) {
        validate(Objects.nonNull(key), new NovelBoardException("ParentNovelKey Value is Null."));
        this.key = key;
    }

}
