package com.yoon.relayNovelopment.application;

import com.yoon.relayNovelopment.domain.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.AlternativeJdkIdGenerator;

@SpringBootConfiguration
@RequiredArgsConstructor
public class NovelBoardCreator {

    private final NovelRepository repository;
    private final IdGeneratore idGeneratore;
    private final AlternativeJdkIdGenerator idGenerator;

    public NovelBoard create(NovelCreateCommand command) {
        String id = idGeneratore.gen();
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
