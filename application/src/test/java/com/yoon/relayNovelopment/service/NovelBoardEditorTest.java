package com.yoon.relayNovelopment.service;

import com.yoon.relayNovelopment.domain.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class NovelBoardEditorTest {

    private final NovelBoardId NOVEL_BOARD_ID = NovelBoardId.of("boardId");

    NovelBoardEditor sut;

    NovelBoardRepository novelBoardRepository;

    @BeforeEach
    void setUp() {
        novelBoardRepository = new FakeNovelBoardRepository();
        sut = new NovelBoardEditor(novelBoardRepository,
                                    new NovelCreateFactory(new FakeIdGenerator("any")));
    }

    @Test
    void relay() {
        NovelEditCommand command = new NovelEditCommand(NOVEL_BOARD_ID, WriterId.of("writerId"), Title.of("Title"), Content.of("value"));

        sut.relay(command);

        assertThat(novelBoardRepository.findBy(NOVEL_BOARD_ID).getNovelSize()).isEqualTo(1);
    }

    @Test
    void fork() {
        NovelEditCommand command = new NovelEditCommand(NOVEL_BOARD_ID, WriterId.of("writerId"), Title.of("Title"), Content.of("value"));
        sut.relay(command);

        NovelEditCommand command2 = new NovelEditCommand(NOVEL_BOARD_ID, WriterId.of("writerId2"), Title.of("Title2"), Content.of("value"));
        sut.fork(command2);

        assertThat(novelBoardRepository.findBy(NOVEL_BOARD_ID).getNovelSize()).isEqualTo(2);
    }
}