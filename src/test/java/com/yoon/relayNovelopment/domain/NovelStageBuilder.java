package com.yoon.relayNovelopment.domain;

public class NovelStageBuilder {

    private NovelStageId novelStageId;
    private Opening opening;
    private int depth;

    public static NovelStageBuilder builder() {
        return new NovelStageBuilder();
    }

    public NovelStage build() {
       return new NovelStage(novelStageId, opening, depth);
    }

    public NovelStageBuilder id(String id) {
        this.novelStageId = NovelStageId.of(id);
        return this;
    }

    public NovelStageBuilder opening(NovelId openingId, WriterId writerId, Title title, Content content) {
        this.opening =  Opening.of((OpeningId) openingId, writerId, title, content);
        return this;
    }

    public NovelStageBuilder stage(int depth) {
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

