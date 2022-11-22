package com.yoon.relayNovelopment.application;

import com.yoon.relayNovelopment.domain.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.yoon.relayNovelopment.domain.NovelBoardBuilder.*;
import static org.assertj.core.api.Assertions.assertThat;

class ParentNovelFinderTest {

    // FIXME 뭔가.. 잘 안 읽힌다.

    ParentNovelFinder sut;

    NovelBoardBuilder novelBoardBuilder;

    @BeforeEach
    void setUp() {
        sut = new ParentNovelFinder();
        novelBoardBuilder = NovelBoardBuilder.builder();
    }

    @Test
    void 최초_relay_될_때_parentNovelId는_openingId_다() {
        NovelBoard board = novelBoardBuilder
                            .opening(openingId("id")).build();

        NovelId parentId = sut.getParentBy(board);
        board.relay(novel(relayId("id2"), parentId));

        assertThat(parentId).isEqualTo(openingId("id"));
    }

    @Test
    void n번째_relay_될_때_parentNovelId는_직전_relayNovelId_다() {
        NovelBoard novelBoard = novelBoardBuilder
                                .opening(openingId("id")).build();

        NovelId parentNovelIdAtFirst = sut.getParentBy(novelBoard);
        novelBoard.relay(novel(relayId("id2"), parentNovelIdAtFirst));

        assertThat(parentNovelIdAtFirst).isEqualTo(openingId("id"));

        NovelId parentNovelIdAtTwice = sut.getParentBy(novelBoard);
        novelBoard.relay(novel2(relayId("id3"), parentNovelIdAtTwice));

        assertThat(parentNovelIdAtTwice).isEqualTo(relayId("id2"));
    }


    @Test
    void fork_될_때_parentNovelId는_직전_relayNovel의_parentNovelId_다() {
        NovelBoard novelBoard = novelBoardBuilder
                                    .opening(openingId("id")).build();

        NovelId parentNovelId = sut.getParentBy(novelBoard);
        novelBoard.relay(novel(relayId("id2"), parentNovelId));

        NovelId parentNovelIdForFork = sut.getParentForForkBy(novelBoard);
        novelBoard.fork(novel2(relayId("id3"), parentNovelIdForFork));

        assertThat(parentNovelId).isEqualTo(openingId("id"));
        assertThat(parentNovelId).isEqualTo(parentNovelIdForFork);
    }

    private Novel novel(RelayNovelId id, NovelId parentId) {
        return Novel.of(id, parentId, WriterId.of("any"), Title.of("any"), Content.of(ContentId.of("conId"), "value"), new Props());
    }

    private Novel novel2(RelayNovelId id, NovelId parentId) {
        return Novel.of(id, parentId, WriterId.of("any2"), Title.of("any2"), Content.of(ContentId.of("conId2"), "value2"), new Props());
    }
}