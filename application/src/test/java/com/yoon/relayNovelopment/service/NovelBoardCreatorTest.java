package com.yoon.relayNovelopment.service;

import com.yoon.relayNovelopment.domain.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class NovelBoardCreatorTest {

    private final NovelBoardId NOVEL_BOARD_ID = NovelBoardId.of("boardId");

    NovelBoardCreator sut;

    NovelRepository novelRepository;

    @BeforeEach
    void setUp() {
        novelRepository = new FakeNovelRepository();
        sut = new NovelBoardCreator(novelRepository,
                                    new FakeIdGenerator(NOVEL_BOARD_ID.getId()));
    }

    @Test
    void create() {
        NovelCreateCommand command = new NovelCreateCommand(WriterId.of("writerId"), Title.of("Title"), Content.of("value")); // title 과 writer 가 필요할까?

        sut.create(command);

        assertThat(novelRepository.findBy(NOVEL_BOARD_ID).getNovelSize()).isEqualTo(0);
    }

    // TODO
    @Test
    void validTest() {

    }
}