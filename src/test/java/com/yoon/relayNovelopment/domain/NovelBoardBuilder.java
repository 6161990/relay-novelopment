package com.yoon.relayNovelopment.domain;

public class NovelBoardBuilder {

    private NovelBoard novelBoard;
    private NovelBoardId novelBoardId;
    private Opening opening;
    private int depth;

    public static NovelBoardBuilder builder() {
        return new NovelBoardBuilder();
    }

    public NovelBoard build() {
        this.novelBoard = new NovelBoard(novelBoardId, opening, depth);
        return novelBoard;
    }

    public NovelBoardBuilder id(String id) {
        this.novelBoardId = NovelBoardId.of(id);
        return this;
    }

    public NovelBoardBuilder opening(NovelId openingId, WriterId writerId, Title title, Content content) {
        this.opening =  Opening.of((OpeningId) openingId, writerId, title, content);
        return this;
    }

    public NovelBoardBuilder maxRelay(int depth) {
        this.depth = depth;
        return this;
    }

    protected static Content content(String contentId, String content) {
        return Content.of(ContentId.of(contentId), content);
    }

    protected static OpeningId openingId(String id) {
        return OpeningId.of(id);
    }

    protected static RelayNovelId relayId(String id) {
        return RelayNovelId.of(id);
    }

    protected static RelayNovelId parentId(String id) {
        return RelayNovelId.of(id);
    }

    protected static WriterId writerId(String id) {
        return WriterId.of(id);
    }

    protected static Title title(String name) {
        return Title.of(name);
    }

}

