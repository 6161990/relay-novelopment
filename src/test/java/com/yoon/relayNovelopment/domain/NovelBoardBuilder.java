package com.yoon.relayNovelopment.domain;

import java.util.ArrayList;
import java.util.List;

public class NovelBoardBuilder {

    private NovelStageId novelStageId;
    private Opening opening;
    private int depth;
    private List<Novel> novelList = new ArrayList<>();

    public static NovelBoardBuilder builder() {
        return new NovelBoardBuilder();
    }

    public NovelBoard build() {
       return new NovelBoard(novelStageId, opening, depth);
    }

    public int getNovelSize(){
        return this.novelList.size();
    }

    public NovelBoardBuilder id(String id) {
        this.novelStageId = NovelStageId.of(id);
        return this;
    }

    public NovelBoardBuilder opening(NovelId openingId, WriterId writerId, Title title, Content content) {
        this.opening =  Opening.of((OpeningId) openingId, writerId, title, content);
        return this;
    }

    public NovelBoardBuilder relay(RelayNovelId id, NovelId parentId , WriterId writer, Title title) {
        this.novelList.add(Novel.of(id, parentId, writer, title, Content.of(ContentId.of("conId"), "Value"), new Props()));
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

    protected static WriterId writerId(String id) {
        return WriterId.of(id);
    }

    protected static Title title(String name) {
        return Title.of(name);
    }

}

