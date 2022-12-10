package com.yoon.relayNovelopment.service;

import com.yoon.relayNovelopment.config.IdGenerator;
import com.yoon.relayNovelopment.domain.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static org.valid4j.Validation.validate;

// TODO : domain module 의 application 으로 옮겨야한다.
//  해당 객체들은 config 에서 bean 으로 관리 되어야 한다.

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
