package com.yoon.relayNovelopment.application;

import com.yoon.relayNovelopment.domain.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NovelBoardCreator {

    private final NovelRepository repository;
    private final IdGenerator idGenerator;

    public NovelBoard create(NovelCreateCommand command) {
        NovelBoard novelBoard = new NovelBoard(NovelBoardId.of(idGenerator.getId()),
                Opening.of(OpeningId.of(idGenerator.getId()),
                        command.getWriterId(),
                        command.getTitle(),
                        command.getContent()));

        repository.save(novelBoard);

        return novelBoard;
    }
}
