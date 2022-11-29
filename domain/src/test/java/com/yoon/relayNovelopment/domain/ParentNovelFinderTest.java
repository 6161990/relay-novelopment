package com.yoon.relayNovelopment.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.yoon.relayNovelopment.domain.NovelBoardBuilder.*;
import static org.assertj.core.api.Assertions.assertThat;

class ParentNovelFinderTest {

    // FIXME 뭔가.. 잘 안 읽힌다.

    ParentNovelFinder sut;

    NovelBoardBuilder novelBoardBuilder;

    OpeningId openingId;


    @BeforeEach
    void setUp() {
        sut = new ParentNovelFinder();
        novelBoardBuilder = NovelBoardBuilder.builder();
    }

    @Test
    void 최초_relay_될_때_parentNovelId는_openingId_다() {
        openingId = openingId("id");
        NovelBoard board = novelBoardBuilder.opening(openingId).build();

        NovelId parentId = sut.getParentBy(board);
        board.relay(novel(relayId("any"), parentId));

        assertThat(parentId).isEqualTo(openingId);
    }

    @Test
    void n번째_relay_될_때_parentNovelId는_직전_relayNovelId_다() {
        openingId = openingId("id");
        NovelBoard novelBoard = novelBoardBuilder.opening(openingId).build();

        NovelId parentNovelIdAtFirst = sut.getParentBy(novelBoard);
        novelBoard.relay(novel(relayId("id2"), parentNovelIdAtFirst));

        assertThat(parentNovelIdAtFirst).isEqualTo(openingId);

        NovelId parentNovelIdAtTwice = sut.getParentBy(novelBoard);
        novelBoard.relay(novel2(relayId("id3"), parentNovelIdAtTwice));

        assertThat(parentNovelIdAtTwice).isEqualTo(relayId("id2"));
    }


    @Test
    void fork_될_때_parentNovelId는_직전_relayNovel의_parentNovelId_다() {
        openingId = openingId("id");
        NovelBoard novelBoard = novelBoardBuilder
                                    .opening(openingId).build();

        NovelId parentNovelId = sut.getParentBy(novelBoard);
        novelBoard.relay(novel(relayId("id2"), parentNovelId));

        assertThat(parentNovelId).isEqualTo(openingId);

        NovelId parentNovelIdForFork = sut.getParentForForkBy(novelBoard);
        novelBoard.fork(novel2(relayId("id3"), parentNovelIdForFork));

        assertThat(parentNovelId).isEqualTo(parentNovelIdForFork).isEqualTo(openingId("id"));
    }

    private Novel novel(RelayNovelId id, NovelId parentId) {
        return Novel.of(id, parentId, WriterId.of("any"), Title.of("any"), Content.of("value"));
    }

    private Novel novel2(RelayNovelId id, NovelId parentId) {
        return Novel.of(id, parentId, WriterId.of("any2"), Title.of("any2"), Content.of("value2"));
    }
}