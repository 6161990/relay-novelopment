package com.yoon.relayNovelopment.domain;

public class NovelBoardBuilder {

    private NovelBoard novelBoard;
    private NovelBoardId novelBoardId;
    private Opening opening;

    public static NovelBoardBuilder builder() {
        return new NovelBoardBuilder();
    }

    public NovelBoard build() {
        this.novelBoard = new NovelBoard(novelBoardId, opening);
        return novelBoard;
    }

    public NovelBoardBuilder id(String id) {
        this.novelBoardId = NovelBoardId.of(id);
        return this;
    }

    public NovelBoardBuilder opening(OpeningKey openingId, WriterId writerId, Title title, Content content) {
        this.opening =  Opening.of(openingId, writerId, title, content);
        return this;
    }

    public NovelBoardBuilder opening(OpeningKey id) {
        this.opening =  Opening.of(id, WriterId.of("any"), Title.of("any"), Content.of("any"));
        return this;
    }

    public static Content content(String content) {
        return Content.of(content);
    }

    public static OpeningKey openingId(String id) {
        return OpeningKey.of(id);
    }

    public static RelayNovelKey relayId(String id) {
        return RelayNovelKey.of(id);
    }

    public static RelayNovelKey parentId(String id) {
        return RelayNovelKey.of(id);
    }

    public static WriterId writerId(String id) {
        return WriterId.of(id);
    }

    public static Title title(String name) {
        return Title.of(name);
    }
}

