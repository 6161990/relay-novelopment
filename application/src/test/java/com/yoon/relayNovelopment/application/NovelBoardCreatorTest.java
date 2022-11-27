package com.yoon.relayNovelopment.application;

import com.yoon.relayNovelopment.domain.*;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.AlternativeJdkIdGenerator;
import org.springframework.util.IdGenerator;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class NovelBoardCreatorTest {

    @Autowired
    private AlternativeJdkIdGenerator idGenerator;

    @Test
    void create() {
        FakeNovelRepository repository = new FakeNovelRepository();
        NovelBoardCreator novelBoardCreator = new NovelBoardCreator(repository, new IdGeneratore(), idGenerator);
        NovelCreateCommand command = new NovelCreateCommand(WriterId.of("writerId"), Title.of("Title"), Content.of(ContentId.of("conId"), "value")); // title 과 writer 가 필요할까?

        NovelBoard novelBoard = novelBoardCreator.create(command);

        assertThat(novelBoard.getOpening().getContent().getId()).isEqualTo(ContentId.of("conId"));
    }
}