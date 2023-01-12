package com.yoon.relayNovelopment.domain;

import lombok.Value;

import java.util.Objects;

import static org.valid4j.Validation.validate;

@Value(staticConstructor = "of")
public class Opening  {
    OpeningKey openingKey;
    WriterId writerId;
    Title title;
    Content content;

    private Opening(OpeningKey openingKey, WriterId writerId, Title title, Content content) {
        preValid(openingKey, writerId, title, content);
        this.openingKey = openingKey;
        this.writerId = writerId;
        this.title = title;
        this.content = content;
    }

    private void preValid(OpeningKey openingKey, WriterId writerId, Title title, Content content) {
        validate(Objects.nonNull(openingKey), new NovelBoardException("NovelKey is Null."));
        validate(Objects.nonNull(writerId), new NovelBoardException("WriterId is Null."));
        validate(Objects.nonNull(title), new NovelBoardException("Title is Null."));
        validate(Objects.nonNull(content), new NovelBoardException("Content is Null."));
    }

}
