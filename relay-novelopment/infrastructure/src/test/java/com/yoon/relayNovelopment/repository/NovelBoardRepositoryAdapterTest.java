package com.yoon.relayNovelopment.repository;

import com.yoon.relayNovelopment.domain.*;
import com.yoon.relayNovelopment.entity.SpringJdbcNovelBoard;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

class NovelBoardRepositoryAdapterTest {

    NovelBoardRepositoryAdapter sut;

    SpringJdbcNovelRepository repository = mock(SpringJdbcNovelRepository.class);
    @BeforeEach
    void setUp() {
        sut = new NovelBoardRepositoryAdapter(repository);
    }

    @Test
    void findByNovelBoardId() {
        Optional<SpringJdbcNovelBoard> springJdbcNovelBoard
                = init("boardId", "key", "writer", "title", "content");

        given(repository.findByNovelBoardId(NovelBoardId.of("boardId"))).willReturn(springJdbcNovelBoard);

        NovelBoard novelBoard = sut.findBy(NovelBoardId.of("boardId"));

        assertThat(novelBoard.getNovelBoardId()).isEqualTo(NovelBoardId.of("boardId"));
        assertThat(novelBoard.getOpening().getOpeningKey()).isEqualTo(OpeningKey.of("key"));
    }

    @Test
    void findAll() {
        List<SpringJdbcNovelBoard> springJdbcNovelBoards
                = Arrays.asList(init("boardId1").get(),
                                init("boardId2").get());

        given(repository.findAll()).willReturn(springJdbcNovelBoards);

        List<NovelBoard> novelBoards = sut.findAll();

        assertThat(novelBoards.stream().map(NovelBoard::getNovelBoardId).collect(Collectors.toList()))
                .containsExactly(NovelBoardId.of("boardId1"), NovelBoardId.of("boardId2"));
    }

    @Test
    void when_not_exist_findByNovelBoardId() {
        given(repository.findByNovelBoardId(NovelBoardId.of("boardId"))).willReturn(Optional.empty());

        NovelBoard id = sut.findBy(NovelBoardId.of("boardId"));

        assertThat(id).isNull();
    }

    private Optional<SpringJdbcNovelBoard> init(String boardId) {
        return Optional.ofNullable(SpringJdbcNovelBoard.builder().novelBoardId(NovelBoardId.of(boardId)).build());
    }

    private Optional<SpringJdbcNovelBoard> init(String boardId, String key, String writer, String title, String content) {
        return Optional.ofNullable(SpringJdbcNovelBoard.builder().novelBoardId(NovelBoardId.of(boardId))
                .opening(Opening.of(OpeningKey.of(key), WriterId.of(writer), Title.of(title), Content.of(content))).build());
    }
}