package com.yoon.relayNovelopment.repository;

import com.yoon.relayNovelopment.domain.NovelBoard;
import com.yoon.relayNovelopment.domain.NovelBoardId;
import com.yoon.relayNovelopment.entity.SpringJdbcNovelBoard;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

class NovelRepositoryAdapterTest {

    NovelRepositoryAdapter sut;

    SpringJdbcNovelRepository repository = mock(SpringJdbcNovelRepository.class);
    @BeforeEach
    void setUp() {
        sut = new NovelRepositoryAdapter(repository);
    }

    @Test
    void findByNovelBoardId() {
        Optional<SpringJdbcNovelBoard> springJdbcNovelBoard = init("boardId");

        given(repository.findBy(NovelBoardId.of("boardId"))).willReturn(springJdbcNovelBoard);

        NovelBoard id = sut.findBy(NovelBoardId.of("boardId"));

        assertThat(id).isEqualTo(id);
    }

    @Test
    void findAll() {
        List<SpringJdbcNovelBoard> springJdbcNovelBoards
                = Arrays.asList(init("boardId1").get(), init("boardId2").get());

        given(repository.findAll()).willReturn(springJdbcNovelBoards);

        List<NovelBoard> novelBoards = sut.findAll();

        assertThat(novelBoards.stream().map(NovelBoard::getNovelBoardId).collect(Collectors.toList()))
                .containsExactly(NovelBoardId.of("boardId1"), NovelBoardId.of("boardId2"));
    }

    @Test
    void when_not_exist_findByNovelBoardId() {
        given(repository.findBy(NovelBoardId.of("boardId"))).willReturn(Optional.empty());

        NovelBoard id = sut.findBy(NovelBoardId.of("boardId"));

        assertThat(id).isNull();
    }

    private Optional<SpringJdbcNovelBoard> init(String boardId) {
        return Optional.ofNullable(SpringJdbcNovelBoard.builder().id(NovelBoardId.of(boardId)).build());
    }
}