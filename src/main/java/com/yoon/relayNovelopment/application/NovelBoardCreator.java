package com.yoon.relayNovelopment.application;

import com.yoon.relayNovelopment.domain.*;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class NovelBoardCreator {

    private final NovelRepository repository;
    private final IdGenerator idGenerator;

    public void create(NovelCreateCommand command) {
        NovelBoard novelBoard = new NovelBoard(NovelBoardId.of(idGenerator.gen()),
                Opening.of(OpeningId.of(idGenerator.gen()),
                        command.getWriterId(),
                        command.getTitle(),
                        command.getContent()));

        repository.save(novelBoard);
    }
}
