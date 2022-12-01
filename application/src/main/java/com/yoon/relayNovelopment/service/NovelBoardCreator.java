package com.yoon.relayNovelopment.service;

import com.yoon.relayNovelopment.config.IdGenerator;
import com.yoon.relayNovelopment.domain.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NovelBoardCreator {

    private final NovelRepository repository;
    private final IdGenerator idGenerator;

    public void create(NovelCreateCommand command) {
        // TODO command 값에 대한 valid

        NovelBoard novelBoard = new NovelBoard(NovelBoardId.of(idGenerator.getId()),
                Opening.of(OpeningKey.of(idGenerator.getId()),
                        command.getWriterId(),
                        command.getTitle(),
                        command.getContent()));

        repository.save(novelBoard);
    }
}
