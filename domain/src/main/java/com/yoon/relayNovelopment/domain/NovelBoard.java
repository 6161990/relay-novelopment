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

        valid(novel);

        this.novels.add(novel);
    }

    public void fork(Novel novel) {
        validForFork(novel);

        this.novels.add(novel);
    }

    public void close() {
        this.isClosed = true;
    }

    public int getNovelSize(){
        return this.novels == null ? 0 : this.novels.size();
    }

    private void init() {
        this.novels = new Novels(new ArrayList<>());
    }

    private void validForFork(Novel novel){
        validate(Objects.nonNull(novels), new NovelBoardException("Novels is Empty."));
        validate(novels.existSameParent(novel.getParentNovelKey()), new NovelBoardException("Not Exist Same Parent Novel."));
        valid(novel);
    }

    private void valid(Novel novel) {
        validate(novels.isNotExist(novel.getWriterId()), new NovelBoardException(String.format("Already exist the writer. WriterId %s, NovelBoardId %s", novel.getWriterId(), novelBoardId)));
        validate(novels.isNotExist(novel.getTitle()),  new NovelBoardException(String.format("Already exist the title. WriterId %s, NovelBoardId %s", novel.getWriterId(), novelBoardId)));
        validate(!isClosed, new NovelBoardException("Already closed."));
    }


}
