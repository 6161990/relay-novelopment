package com.yoon.relayNovelopment.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.yoon.relayNovelopment.domain.NovelBoardBuilder.*;
import static org.assertj.core.api.Assertions.assertThat;

class ParentNovelFinderTest {

    // FIXME 뭔가.. 잘 안 읽힌다.

    ParentNovelFinder sut;

    NovelBoardBuilder novelBoardBuilder;

    OpeningKey openingId;


    @BeforeEach
    void setUp() {
        sut = new ParentNovelFinder();
        novelBoardBuilder = NovelBoardBuilder.builder();
    }

    @Test
    void 최초_relay_될_때_parentNovelId는_openingId_다() {
        openingId = openingId("id");
        NovelBoard board = novelBoardBuilder.opening(openingId).build();

        NovelKey parentId = sut.getParentBy(board);
        board.relay(novel(relayId("any"), parentId));

        assertThat(parentId).isEqualTo(openingId);
    }

    @Test
    void n번째_relay_될_때_parentNovelId는_직전_relayNovelId_다() {
        openingId = openingId("id");
        NovelBoard novelBoard = novelBoardBuilder.opening(openingId).build();

        NovelKey parentNovelKeyAtFirst = sut.getParentBy(novelBoard);
        novelBoard.relay(novel(relayId("id2"), parentNovelKeyAtFirst));

        assertThat(parentNovelKeyAtFirst).isEqualTo(openingId);

        NovelKey parentNovelKeyAtTwice = sut.getParentBy(novelBoard);
        novelBoard.relay(novel2(relayId("id3"), parentNovelKeyAtTwice));

        assertThat(parentNovelKeyAtTwice).isEqualTo(relayId("id2"));
    }


    @Test
    void fork_될_때_parentNovelId는_직전_relayNovel의_parentNovelId_다() {
        openingId = openingId("id");
        NovelBoard novelBoard = novelBoardBuilder
                                    .opening(openingId).build();

        NovelKey parentNovelKey = sut.getParentBy(novelBoard);
        novelBoard.relay(novel(relayId("id2"), parentNovelKey));

        assertThat(parentNovelKey).isEqualTo(openingId);

        NovelKey parentNovelKeyForFork = sut.getParentForForkBy(novelBoard);
        novelBoard.fork(novel2(relayId("id3"), parentNovelKeyForFork));

        assertThat(parentNovelKey).isEqualTo(parentNovelKeyForFork).isEqualTo(openingId("id"));
    }

    private Novel novel(RelayNovelKey id, NovelKey parentId) {
        return Novel.of(id, parentId, WriterId.of("any"), Title.of("any"), Content.of("value"));
    }

    private Novel novel2(RelayNovelKey id, NovelKey parentId) {
        return Novel.of(id, parentId, WriterId.of("any2"), Title.of("any2"), Content.of("value2"));
    }
}