package com.yoon.relayNovelopment.application;

import com.yoon.relayNovelopment.domain.*;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class NovelBoardEditor {

    private final NovelRepository repository;
    private final NovelCreateFactory createFactory;

    public NovelBoard relay(NovelBoardId novelBoardId, NovelEditCommand command) {
        NovelBoard novelBoard = repository.findBy(novelBoardId);

        Novel novel = createFactory.create(novelBoard, command);

        novelBoard.relay(novel);

        return novelBoard;
    }

    public void fork(NovelBoardId novelBoardId, NovelEditCommand command){

    }
}
