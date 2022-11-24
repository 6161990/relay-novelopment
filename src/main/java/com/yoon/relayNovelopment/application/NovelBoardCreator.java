package com.yoon.relayNovelopment.application;

import com.yoon.relayNovelopment.domain.*;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class NovelBoardCreator {

    private final NovelRepository repository;
    private final IdGenerator idGenerator;

    public NovelBoard create(NovelCreateCommand command) {
        String id = idGenerator.gen();
        NovelBoard novelBoard = new NovelBoard(NovelBoardId.of(id),
                Opening.of(OpeningId.of(idGenerator.keyOpen(id)),
                        command.getWriterId(),
                        command.getTitle(),
                        command.getContent()));

        repository.save(novelBoard);

        return novelBoard;
    }
}
