package com.yoon.relayNovelopment.application;

import com.yoon.relayNovelopment.domain.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NovelBoardEditor {

    private final NovelRepository repository;
    private final NovelCreateFactory createFactory;

    public void relay(NovelEditorCommand command) {
        NovelBoard novelBoard = repository.findBy(command.getNovelBoardId());

        Novel novel = createFactory.create(novelBoard, command); // create - relay
        novelBoard.relay(novel);

        repository.save(novelBoard);
    }

    public void fork(NovelEditorCommand command){
        NovelBoard novelBoard = repository.findBy(command.getNovelBoardId());

        Novel novel = createFactory.createForFork(novelBoard, command); // createForFork - fork
        novelBoard.fork(novel);

        repository.save(novelBoard);
    }
}
