package com.yoon.relayNovelopment.domain;

class NovelBoardBuilderForTest {

    private NovelBoard novelBoard;
    private NovelBoardId novelBoardId;
    private Opening opening;

    static NovelBoardBuilderForTest builder() {
        return new NovelBoardBuilderForTest();
    }

    NovelBoard build() {
        this.novelBoard = new NovelBoard(novelBoardId, opening);
        return novelBoard;
    }

    NovelBoardBuilderForTest id(String id) {
        this.novelBoardId = NovelBoardId.of(id);
        return this;
    }

    NovelBoardBuilderForTest opening(OpeningKey openingId, WriterId writerId, Title title, Content content) {
        this.opening =  Opening.of(openingId, writerId, title, content);
        return this;
    }

    NovelBoardBuilderForTest opening(OpeningKey key) {
        this.opening =  Opening.of(key, WriterId.of("any"), Title.of("any"), Content.of("any"));
        return this;
    }

    static Content content(String content) {
        return Content.of(content);
    }

    static OpeningKey openingKey(String key) {
        return OpeningKey.of(key);
    }

    static RelayNovelKey relayKey(String key) {
        return RelayNovelKey.of(key);
    }

    static RelayNovelKey parentKey(String key) {
        return RelayNovelKey.of(key);
    }

    static WriterId writerId(String id) {
        return WriterId.of(id);
    }

    static Title title(String name) {
        return Title.of(name);
    }
}

