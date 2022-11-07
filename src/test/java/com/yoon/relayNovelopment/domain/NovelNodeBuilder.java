package com.yoon.relayNovelopment.domain;

public class NovelNodeBuilder {

    private NovelNode node;
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

    public NovelNodeBuilder openingNovel(String id, String writer, String title, Content content, String props) {
        this.openingNovel =  OpeningNovel.of(OpeningNovelId.of(id), WriterId.of(writer), Title.of(title), content, new Props());
        return this;
    }

    public NovelNodeBuilder depth(int depth) {
        this.depth = depth;
        return this;
    }

    protected static Content content(String contentId, String content) {
        return Content.of(ContentId.of(contentId), content);
    }
}
