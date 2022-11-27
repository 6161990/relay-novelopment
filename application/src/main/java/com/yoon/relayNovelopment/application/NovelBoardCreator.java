package com.yoon.relayNovelopment.application;

import com.yoon.relayNovelopment.domain.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.AlternativeJdkIdGenerator;

@Service
@RequiredArgsConstructor
public class NovelBoardCreator {

    private final NovelRepository repository;
    private final AlternativeJdkIdGenerator idGenerator;

    public NovelBoard create(NovelCreateCommand command) {
        String novelBoardId = idGenerator.generateId().toString();
        String openId = idGenerator.generateId().toString();
        NovelBoard novelBoard = new NovelBoard(NovelBoardId.of(novelBoardId),
                Opening.of(OpeningId.of(openId),
                        command.getWriterId(),
                        command.getTitle(),
                        command.getContent()));

        repository.save(novelBoard);

        return novelBoard;
    }
}
