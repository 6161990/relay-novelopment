package com.yoon.relayNovelopment.domain;

import lombok.Getter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

import static org.valid4j.Validation.validate;

@Getter
@ToString
public class NovelBoard {
    private final NovelBoardId id;
    private final Opening opening;
    private int maxRelaySize;

    @Getter
    private List<Novel> novels;

    public NovelBoard(NovelBoardId id, Opening opening, int maxRelaySize) {
        this.id = id;
        this.opening = opening;
        this.maxRelaySize = maxRelaySize;
    }

    public void relay(Novel novel) {
        if (novels == null || novels.isEmpty()){
            init();
        }

        valid(novel); // TODO maxStageSize 가 일정값 이상이면 throw = 소설이 완성되는 것은 언제인가.

        this.novels.add(novel);
        maxRelaySize++;
    }

    public void fork(Novel novel) { // TODO 같은 부모 parentNovelId 를 가지고 있는 novel 이 추가될 때 (옆으로 추가될 때)
        if (novels == null || novels.isEmpty()){
            throw new NovelNodeException("Novels is Empty");
        }

        validForFork(novel);

        this.novels.add(novel);
    }

    private void init() {
        this.novels = new ArrayList<>();
    }

    public int getNovelSize(){
        return this.novels == null ? 0 : this.novels.size();
    }

    private void valid(Novel novel) {
        validate(novels.stream().noneMatch(n-> n.getWriterId().getId().equals(novel.getWriterId().getId())),
                new NovelNodeException(String.format("Already exist the writer. WriterId %s, NovelBoardId %s", novel.getWriterId(), id)));
        validate(novels.stream().noneMatch(n-> n.getTitle().equals(novel.getTitle())),
                new NovelNodeException(String.format("Already exist the title. WriterId %s, NovelBoardId %s", novel.getWriterId(), id)));
    }

    private void validForFork(Novel novel){
        valid(novel);
        validate(novels.stream().anyMatch(e->e.getParentNovelId().equals(novel.getParentNovelId())),
                new NovelNodeException("Not Exist Same Parent Novel.")); // FIXME ExceptionMessage 가 이게 맞나 ?
    }

    public int getSameParentSizeBy(NovelId id) {
        return (int) novels.stream().filter(i -> i.getParentNovelId().equals(id)).count();
    }
}
