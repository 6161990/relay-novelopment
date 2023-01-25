package com.yoon.relayNovelopment.service;

import com.yoon.relayNovelopment.domain.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ArticleBoardEditorTest {

    private final NovelBoardId NOVEL_BOARD_ID = NovelBoardId.of("boardId");

    NovelBoardEditor sut;

    NovelBoardRepository novelBoardRepository;

    @BeforeEach
    void setUp() {
        novelBoardRepository = new FakeNovelBoardRepository();
        sut = new NovelBoardEditor(novelBoardRepository,
                                    new ArticleCreateFactory(new FakeIdGenerator("any"), new ParentNovelKeyFinder()));
    }

    @Test
    void relay() {
        NovelBoardEditCommand command = new NovelBoardEditCommand(NOVEL_BOARD_ID, WriterId.of("writerId"), Title.of("Title"), Content.of("value"), Genre.NOVEL);

        sut.relay(command);

        assertThat(novelBoardRepository.findBy(NOVEL_BOARD_ID).getNovelSize()).isEqualTo(1);
    }

    @Test
    void fork() {
        NovelBoardEditCommand command = new NovelBoardEditCommand(NOVEL_BOARD_ID, WriterId.of("writerId"), Title.of("Title"), Content.of("value"), Genre.NOVEL);
        sut.relay(command);

        NovelBoardEditCommand command2 = new NovelBoardEditCommand(NOVEL_BOARD_ID, WriterId.of("writerId2"), Title.of("Title2"), Content.of("value"), Genre.NOVEL);
        sut.fork(command2);

        assertThat(novelBoardRepository.findBy(NOVEL_BOARD_ID).getNovelSize()).isEqualTo(2);
    }
}