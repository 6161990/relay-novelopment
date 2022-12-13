package com.yoon.relayNovelopment.service;

import com.yoon.relayNovelopment.domain.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NovelBoardEditor {

    private final NovelRepository repository;
    private final NovelCreateFactory createFactory;

    public void relay(NovelEditorCommand command) {
        NovelBoard novelBoard = repository.findBy(command.getNovelBoardId()).orElseThrow(()
                -> new NovelBoardNotFoundException(String.format("This NovelBoard Not Found. NovelBoardId is %s.", command.getNovelBoardId())));

        Novel novel = createFactory.createForRelay(novelBoard, command); // create - relay
        novelBoard.relay(novel);

        repository.save(novelBoard);
    }

    public void fork(NovelEditorCommand command){
        NovelBoard novelBoard = repository.findBy(command.getNovelBoardId()).orElseThrow(()
                -> new NovelBoardNotFoundException(String.format("This NovelBoard Not Found. NovelBoardId is %s.", command.getNovelBoardId())));

        Novel novel = createFactory.createForFork(novelBoard, command); // createForFork - fork
        novelBoard.fork(novel);

        repository.save(novelBoard);
    }

    public void remove(NovelBoardId id) {
        repository.remove(id);
    }
}
