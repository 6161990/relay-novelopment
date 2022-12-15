package com.yoon.relayNovelopment.domain;

import java.util.Objects;

import static org.valid4j.Validation.validate;

public class Novel {
    private final RelayNovelKey novelKey;
    private final NovelKey parentNovelKey;
    private final WriterId writerId;
    private final Title title;
    private final Content content;

    public static Novel of(RelayNovelKey novelKey, NovelKey parentNovelKey, WriterId writerId, Title title, Content content) {
        preValid(novelKey, parentNovelKey, writerId, title, content);
        return new Novel(novelKey, parentNovelKey, writerId, title, content);
    }

    private static void preValid(RelayNovelKey novelKey, NovelKey parentNovelKey, WriterId writerId, Title title, Content content) {
        validate(Objects.nonNull(novelKey), new NovelBoardException("NovelKey is Null."));
        validate(Objects.nonNull(parentNovelKey), new NovelBoardException("ParentNovelKey is Null."));
        validate(Objects.nonNull(writerId), new NovelBoardException("WriterId is Null."));
        validate(Objects.nonNull(title), new NovelBoardException("Title is Null."));
        validate(Objects.nonNull(content), new NovelBoardException("Content is Null."));
    }

    private Novel(RelayNovelKey novelKey, NovelKey parentNovelKey, WriterId writerId, Title title, Content content) {
        this.novelKey = novelKey;
        this.parentNovelKey = parentNovelKey;
        this.writerId = writerId;
        this.title = title;
        this.content = content;
    }

    public RelayNovelKey getNovelKey() {
        return novelKey;
    }

    public NovelKey getParentNovelKey() {
        return parentNovelKey;
    }

    public WriterId getWriterId() {
        return writerId;
    }

    public Title getTitle() {
        return title;
    }

    public Content getContent() {
        return content;
    }
}
