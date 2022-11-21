package com.yoon.relayNovelopment.application;

import com.yoon.relayNovelopment.domain.*;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class NovelBoardEditor {

    private final NovelRepository repository;
    private final ParentFinder parentFinder;
    private final NovelGenerator generator;

    public NovelBoard relay(NovelBoardId novelBoardId, NovelEditCommand command) {
        NovelBoard novelBoard = repository.findBy(novelBoardId);

        NovelId parentId = parentFinder.findBy(novelBoard);

        Novel novel = generator.gen(parentId, command);

        novelBoard.relay(novel);

        return novelBoard;
    }

    public void fork(NovelBoardId novelBoardId, NovelEditCommand command){

    }
}
