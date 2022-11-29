package com.yoon.relayNovelopment.application;

import com.yoon.relayNovelopment.domain.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

class NovelBoardCreatorTest {

    @Test
    void create() {
        FakeNovelRepository repository = new FakeNovelRepository();
        NovelBoardCreator novelBoardCreator = new NovelBoardCreator(repository, new FakeIdGenerator("BoardId"));
        NovelCreateCommand command = new NovelCreateCommand(WriterId.of("writerId"), Title.of("Title"), Content.of("value")); // title 과 writer 가 필요할까?

        NovelBoard novelBoard = novelBoardCreator.create(command);

        assertThat(novelBoard.getOpening().getContent().getValue()).isEqualTo("value");
    }
}