package com.yoon.relayNovelopment.application;

import com.yoon.relayNovelopment.domain.*;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class NovelBoardEditor {

    private final NovelRepository repository;
    private final NovelCreateFactory createFactory;

    public NovelBoard relay(NovelBoardId novelBoardId, NovelEditCommand command) {
        NovelBoard novelBoard = repository.findBy(novelBoardId);

        Novel novel = createFactory.create(novelBoard, command); // create - relay
        novelBoard.relay(novel);

        repository.save(novelBoard);

        return novelBoard;
    }

    public NovelBoard fork(NovelBoardId novelBoardId, NovelEditCommand command){
        NovelBoard novelBoard = repository.findBy(novelBoardId);

        Novel novel = createFactory.createForFork(novelBoard, command); // createForFork - fork
        novelBoard.fork(novel);

        repository.save(novelBoard);

        return novelBoard;
    }
}
