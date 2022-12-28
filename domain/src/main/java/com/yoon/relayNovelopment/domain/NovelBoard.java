package com.yoon.relayNovelopment.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Objects;

import static org.valid4j.Validation.validate;

@Getter
@AllArgsConstructor
public class NovelBoard {
    private final NovelBoardId novelBoardId;
    private final Opening opening;
    private boolean isClosed;
    private Novels novels;
    private LocalDateTime createdAt;
    private LocalDateTime deleteAt;
    private Long version;

    public NovelBoard(NovelBoardId novelBoardId, Opening opening) {
        this.novelBoardId = novelBoardId;
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

    public int getNovelSize(){
        return this.novels == null ? 0 : this.novels.size();
    }

    private void init() {
        this.novels = new Novels(new ArrayList<>());
    }

    private void validForFork(Novel novel){
        valid(novel);
        validate(!Objects.requireNonNull(novels).isEmpty(), new NovelBoardException("Novels is Empty"));
        validate(novels.existSameParent(novel.getParentNovelKey()), new NovelBoardException("Not Exist Same Parent Novel."));
    }

    private void valid(Novel novel) {
        validate(novels.isNotExist(novel.getWriterId()), new NovelBoardException(String.format("Already exist the writer. WriterId %s, NovelBoardId %s", novel.getWriterId(), novelBoardId)));
        validate(novels.isNotExist(novel.getTitle()),  new NovelBoardException(String.format("Already exist the title. WriterId %s, NovelBoardId %s", novel.getWriterId(), novelBoardId)));
        validate(!isClosed, new NovelBoardException("Already closed."));
    }

}
