package com.yoon.relayNovelopment.service;

import com.yoon.relayNovelopment.domain.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class NovelBoardEditorTest {

    private final NovelBoardId NOVEL_BOARD_ID = NovelBoardId.of("boardId");

    NovelBoardEditor sut;

    NovelRepository novelRepository;

    @BeforeEach
    void setUp() {
        novelRepository = new FakeNovelRepository();
        sut = new NovelBoardEditor(novelRepository,
                                    new NovelCreateFactory(new FakeIdGenerator("any")));
    }

    @Test
    void relay() {
        NovelEditorCommand command = new NovelEditorCommand(NOVEL_BOARD_ID, WriterId.of("writerId"), Title.of("Title"), Content.of("value"));

        sut.relay(command);

        assertThat(novelRepository.findBy(NOVEL_BOARD_ID).getNovelSize()).isEqualTo(1);
    }

    @Test
    void fork() {
        NovelEditorCommand command = new NovelEditorCommand(NOVEL_BOARD_ID, WriterId.of("writerId"), Title.of("Title"), Content.of("value"));
        sut.relay(command);

        NovelEditorCommand command2 = new NovelEditorCommand(NOVEL_BOARD_ID, WriterId.of("writerId2"), Title.of("Title2"), Content.of("value"));
        sut.fork(command2);

        assertThat(novelRepository.findBy(NOVEL_BOARD_ID).getNovelSize()).isEqualTo(2);
    }
}