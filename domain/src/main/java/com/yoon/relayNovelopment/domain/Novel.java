package com.yoon.relayNovelopment.domain;

import lombok.Value;

import java.util.Objects;

import static org.valid4j.Validation.validate;

@Value(staticConstructor = "of")
public class Novel {
    RelayNovelKey relayNovelKey;
    NovelKey parentNovelKey;
    WriterId writerId;
    Title title;
    Content content;

    private Novel(RelayNovelKey relayNovelKey, NovelKey parentNovelKey, WriterId writerId, Title title, Content content) {
        preValid(relayNovelKey, parentNovelKey, writerId, title, content);
        this.relayNovelKey = relayNovelKey;
        this.parentNovelKey = parentNovelKey;
        this.writerId = writerId;
        this.title = title;
        this.content = content;
    }

    private void preValid(RelayNovelKey novelKey, NovelKey parentNovelKey, WriterId writerId, Title title, Content content) {
        validate(Objects.nonNull(novelKey), new NovelBoardException("NovelKey is Null."));
        validate(Objects.nonNull(parentNovelKey), new NovelBoardException("ParentNovelKey is Null."));
        validate(Objects.nonNull(writerId), new NovelBoardException("WriterId is Null."));
        validate(Objects.nonNull(title), new NovelBoardException("Title is Null."));
        validate(Objects.nonNull(content), new NovelBoardException("Content is Null."));
    }

}
