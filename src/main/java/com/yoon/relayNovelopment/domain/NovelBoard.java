package com.yoon.relayNovelopment.domain;

import lombok.Getter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

import static org.valid4j.Validation.validate;

@Getter
@ToString
public class NovelBoard {
    private final NovelStageId id;
    private final Opening opening;
    private int maxStageSize;

    @Getter
    private List<Novel> novels;

    public NovelBoard(NovelStageId id, Opening opening, int maxStageSize) {
        this.id = id;
        this.opening = opening;
        this.maxStageSize = maxStageSize;
    }

    public void relay(Novel novel) {
        if (novels == null || novels.isEmpty()){
            init();
        }

        valid(novel); // TODO maxStageSize 가 일정값 이상이면 throw = 소설이 완성되는 것은 언제인가.

        this.novels.add(novel);
        maxStageSize ++;
    }

    public void fork(Novel novel){ // TODO 같은 부모 parentNovelId 를 가지고 있는 novel 이 추가될 때 (옆으로 추가될 때)
        if (novels == null || novels.isEmpty()){
            throw new NovelNodeException("Novels is Empty");
        }

        valid(novel);

        this.novels.add(novel);
    }

    private void init() {
        this.novels = new ArrayList<>();
    }

    public long getNovelSize(){
        return this.novels == null ? 0 : this.novels.size();
    }

    private void valid(Novel novel) {
        validate(novels.stream().noneMatch(n-> n.getWriterId().getId().equals(novel.getWriterId().getId())),
                new NovelNodeException(String.format("Already exist the writer. WriterId %s, NovelNodeId %s", novel.getWriterId(), id)));
        validate(novels.stream().noneMatch(n-> n.getTitle().equals(novel.getTitle())),
                new NovelNodeException(String.format("Already exist the title. WriterId %s, NovelNodeId %s", novel.getWriterId(), id)));
    }

}
