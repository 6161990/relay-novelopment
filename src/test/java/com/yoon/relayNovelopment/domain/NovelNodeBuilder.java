package com.yoon.relayNovelopment.domain;

public class NovelNodeBuilder {

    private NovelNodeId novelNodeId;
    private OpeningNovel openingNovel;
    private int depth;

    public static NovelNodeBuilder builder() {
        return new NovelNodeBuilder();
    }

    public NovelNode build() {
       return new NovelNode(novelNodeId, openingNovel, depth);
    }

    public NovelNodeBuilder id(String id) {
        this.novelNodeId = NovelNodeId.of(id);
        return this;
    }

    public NovelNodeBuilder openingNovel(NovelId openingNovelId, WriterId writerId, Title title, Content content) {
        this.openingNovel =  OpeningNovel.of((OpeningNovelId) openingNovelId, writerId, title, content, new Props());
        return this;
    }

    public NovelNodeBuilder depth(int depth) {
        this.depth = depth;
        return this;
    }

    protected static Content content(String contentId, String content) {
        return Content.of(ContentId.of(contentId), content);
    }

    protected static OpeningNovelId openingNovelId(String id) {
        return OpeningNovelId.of(id);
    }

    protected static WriterId writerId(String id) {
        return WriterId.of(id);
    }

    protected static Title title(String name) {
        return Title.of(name);
    }
}

