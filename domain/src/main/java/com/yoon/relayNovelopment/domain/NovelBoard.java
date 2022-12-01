package com.yoon.relayNovelopment.domain;

import lombok.Getter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static org.valid4j.Validation.validate;

public class NovelBoard {
    @Getter
    private final NovelBoardId id;
    private final Opening opening;
    private boolean isClosed;
    private List<Novel> novels;

    public NovelBoard(NovelBoardId id, Opening opening) {
        this.id = id;
        this.opening = opening;
    }

    public void relay(Novel novel) {
        if (novels == null || novels.isEmpty()){
            init();
        }

        valid(novel); // TODO maxStageSize 가 일정값 이상이면 throw = 소설이 완성되는 것은 언제인가.

        this.novels.add(novel);
    }

    public void fork(Novel novel) {
        validForFork(novel);

        this.novels.add(novel);
    }

    private void init() {
        this.novels = new ArrayList<>();
    }

    public int getNovelSize(){
        return this.novels == null ? 0 : this.novels.size();
    }

    public int getSameParentSizeBy(NovelKey id) {
        return (int) novels.stream().filter(i -> i.getParentNovelKey().equals(id)).count();
    }

    Opening getOpening() {
        return opening;
    }

    List<Novel> getNovels() {
        return novels;
    }

    private void validForFork(Novel novel){
        valid(novel);
        validate(!Objects.requireNonNull(novels).isEmpty(), new NovelBoardException("Novels is Empty"));
        validate(novels.stream().anyMatch(e->e.getParentNovelKey().equals(novel.getParentNovelKey())),
                new NovelBoardException("Not Exist Same Parent Novel.")); // FIXME ExceptionMessage 가 이게 맞나 ?
    }

    private void valid(Novel novel) {
        validate(novels.stream().noneMatch(n-> n.getWriterId().getId().equals(novel.getWriterId().getId())),
                new NovelBoardException(String.format("Already exist the writer. WriterId %s, NovelBoardId %s", novel.getWriterId(), id)));
        validate(novels.stream().noneMatch(n-> n.getTitle().equals(novel.getTitle())),
                new NovelBoardException(String.format("Already exist the title. WriterId %s, NovelBoardId %s", novel.getWriterId(), id)));
        validate(!isClosed, new NovelBoardException("Already closed."));
    }

}
