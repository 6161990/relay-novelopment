package com.yoon.relayNovelopment.domain;

class NovelBoardBuilderForTest {

    private NovelBoard novelBoard;
    private NovelBoardId novelBoardId;
    private Opening opening;
    private Article article;

    private boolean isClosed;

    static NovelBoardBuilderForTest builder() {
        return new NovelBoardBuilderForTest();
    }

    NovelBoard build() {
        this.novelBoard = new NovelBoard(novelBoardId, opening, Genre.NOVEL);
        return novelBoard;
    }
    NovelBoard buildForRelay() {
        this.novelBoard = new NovelBoard(novelBoardId, opening, Genre.NOVEL);
        novelBoard.relay(article);
        if(isClosed){this.novelBoard.close();}
        return novelBoard;
    }

    NovelBoard buildForFork() {
        this.novelBoard = new NovelBoard(novelBoardId, opening, Genre.NOVEL);
        novelBoard.fork(article);
        return novelBoard;
    }

    NovelBoardBuilderForTest id(String id) {
        this.novelBoardId = NovelBoardId.of(id);
        return this;
    }

    NovelBoardBuilderForTest opening(OpeningKey openingId, WriterId writerId, Title title, Content content) {
        this.opening = Opening.of(openingId, writerId, title, content);
        return this;
    }

    NovelBoardBuilderForTest opening(OpeningKey key) {
        this.opening = Opening.of(key, WriterId.of("any"), Title.of("any"), Content.of("any"));
        return this;
    }

    public NovelBoardBuilderForTest relay(Article article) {
        this.article = article;
        return this;
    }

    public NovelBoardBuilderForTest isClosed(boolean b) {
        this.isClosed = b;
        return this;
    }

    static Content content(String content) {
        return Content.of(content);
    }

    static OpeningKey openingKey(String key) {
        return OpeningKey.of(key);
    }

    static RelayArticleKey relayKey(String key) {
        return RelayArticleKey.of(key);
    }

    static RelayArticleKey parentKey(String key) {
        return RelayArticleKey.of(key);
    }

    static WriterId writerId(String id) {
        return WriterId.of(id);
    }

    static Title title(String name) {
        return Title.of(name);
    }

}

