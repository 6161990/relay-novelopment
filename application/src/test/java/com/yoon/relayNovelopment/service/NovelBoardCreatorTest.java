package com.yoon.relayNovelopment.service;

import com.yoon.relayNovelopment.domain.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

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
        NovelCreateCommand command = new NovelCreateCommand(WriterId.of("writerId"), Title.of("Title"), Content.of("value"));

        sut.create(command);

        assertThat(novelRepository.findBy(NOVEL_BOARD_ID).getNovelSize()).isEqualTo(0);
    }

    @Nested
    class validTest {

        @Test
        void writerId_는_null_일_수_없다() {
            NovelCreateCommand command = new NovelCreateCommand(null, Title.of("Title"), Content.of("value"));

            assertThatThrownBy(() -> sut.create(command))
                    .isInstanceOf(NovelBoardException.class);
        }

        @Test
        void title_는_null_일_수_없다() {
            NovelCreateCommand command = new NovelCreateCommand(WriterId.of("writerId"), null, Content.of("value"));

            assertThatThrownBy(() -> sut.create(command))
                    .isInstanceOf(NovelBoardException.class);
        }

        @Test
        void content_는_null_일_수_없다() {
            NovelCreateCommand command = new NovelCreateCommand(WriterId.of("writerId"), Title.of("Title"), null);

            assertThatThrownBy(() -> sut.create(command))
                    .isInstanceOf(NovelBoardException.class);
        }
    }

}