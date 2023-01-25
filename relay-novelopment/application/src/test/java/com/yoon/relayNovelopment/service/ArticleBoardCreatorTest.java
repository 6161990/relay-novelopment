package com.yoon.relayNovelopment.service;

import com.yoon.relayNovelopment.domain.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ArticleBoardCreatorTest {

    private final NovelBoardId NOVEL_BOARD_ID = NovelBoardId.of("boardId");

    NovelBoardCreator sut;

    NovelBoardRepository novelBoardRepository;

    @BeforeEach
    void setUp() {
        novelBoardRepository = new FakeNovelBoardRepository();
        sut = new NovelBoardCreator(novelBoardRepository,
                                    new FakeIdGenerator(NOVEL_BOARD_ID.getId()));
    }

    @Test
    void create() {
        NovelBoardCreateCommand command = new NovelBoardCreateCommand(WriterId.of("writerId"), Title.of("Title"), Content.of("value"), Genre.NOVEL);

        sut.create(command);

        assertThat(novelBoardRepository.findBy(NOVEL_BOARD_ID).getNovelSize()).isEqualTo(0);
    }

    @Nested
    class validTest {

        @Test
        void writerId_는_null_일_수_없다() {
            NovelBoardCreateCommand command = new NovelBoardCreateCommand(null, Title.of("Title"), Content.of("value"), Genre.NOVEL);

            assertThatThrownBy(() -> sut.create(command))
                    .isInstanceOf(NovelBoardException.class);
        }

        @Test
        void title_는_null_일_수_없다() {
            NovelBoardCreateCommand command = new NovelBoardCreateCommand(WriterId.of("writerId"), null, Content.of("value"), Genre.NOVEL);

            assertThatThrownBy(() -> sut.create(command))
                    .isInstanceOf(NovelBoardException.class);
        }

        @Test
        void content_는_null_일_수_없다() {
            NovelBoardCreateCommand command = new NovelBoardCreateCommand(WriterId.of("writerId"), Title.of("Title"), null, Genre.NOVEL);

            assertThatThrownBy(() -> sut.create(command))
                    .isInstanceOf(NovelBoardException.class);
        }

        @Test
        void genre_는_null_일_수_없다() {
        }
    }

}