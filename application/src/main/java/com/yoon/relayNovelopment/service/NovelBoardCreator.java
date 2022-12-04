package com.yoon.relayNovelopment.service;

import com.yoon.relayNovelopment.config.IdGenerator;
import com.yoon.relayNovelopment.domain.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static org.valid4j.Validation.validate;

@Service
@RequiredArgsConstructor
public class NovelBoardCreator {

    private final NovelRepository repository;
    private final IdGenerator idGenerator;

    public void create(NovelCreateCommand command) {
        validate(command.writerId != null, new NovelBoardException("WriterId is Null"));
        validate(command.title != null, new NovelBoardException("Title is Null"));
        validate(command.content != null, new NovelBoardException("Content is Null"));

        NovelBoard novelBoard = new NovelBoard(NovelBoardId.of(idGenerator.getId()),
                Opening.of(OpeningKey.of(idGenerator.getId()),
                        command.getWriterId(),
                        command.getTitle(),
                        command.getContent()));

        repository.save(novelBoard);
    }
}