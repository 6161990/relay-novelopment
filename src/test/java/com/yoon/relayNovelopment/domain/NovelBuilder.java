package com.yoon.relayNovelopment.domain;

public class NovelBuilder {

    private NovelNodeId novelNodeId;
    private Opening opening;
    private int depth;

    public static NovelBuilder builder() {
        return new NovelBuilder();
    }

    public Novel build() {
       return new Novel(novelNodeId, opening, depth);
    }

    public NovelBuilder id(String id) {
        this.novelNodeId = NovelNodeId.of(id);
        return this;
    }

    public NovelBuilder opening(NovelId openingId, WriterId writerId, Title title, Content content) {
        this.opening =  Opening.of((OpeningId) openingId, writerId, title, content, new Props());
        return this;
    }

    public NovelBuilder stage(int depth) {
        this.depth = depth;
        return this;
    }

    protected static Content content(String contentId, String content) {
        return Content.of(ContentId.of(contentId), content);
    }

    protected static OpeningId openingId(String id) {
        return OpeningId.of(id);
    }

    protected static WriterId writerId(String id) {
        return WriterId.of(id);
    }

    protected static Title title(String name) {
        return Title.of(name);
    }
}

