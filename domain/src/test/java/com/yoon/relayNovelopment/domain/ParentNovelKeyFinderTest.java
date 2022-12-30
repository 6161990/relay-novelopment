package com.yoon.relayNovelopment.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.yoon.relayNovelopment.domain.NovelBoardBuilderForTest.*;
import static org.assertj.core.api.Assertions.assertThat;

class ParentNovelKeyFinderTest {

    private static final OpeningKey ANY_OPENING_KEY = OpeningKey.of("openKey");
    private static final RelayNovelKey FIRST_NOVEL_KEY = relayKey("novelKey");
    private static final RelayNovelKey SECOND_NOVEL_KEY2 = relayKey("novelKey2");

    private static NovelKey prevParentKey;
    ParentNovelKeyFinder sut;

    NovelBoardBuilderForTest novelBoardBuilderForTest;

    @BeforeEach
    void setUp() {
        sut = new ParentNovelKeyFinder();
        novelBoardBuilderForTest = NovelBoardBuilderForTest.builder();
    }

    @Test
    void 최초_relay_될_때_parentNovelKey는_openingKey_다() {
        NovelBoard board = novelBoardBuilderForTest
                .opening(ANY_OPENING_KEY).build();

        NovelKey parentKey = sut.getParentBy(board);
        board.relay(novel(FIRST_NOVEL_KEY, parentKey));

        assertThat(parentKey).isEqualTo(ANY_OPENING_KEY);
    }

    @Test
    void n번째_relay_될_때_parentNovelKey는_직전_relayNovelKey_다() {
        NovelBoard novelBoard = novelBoardBuilderForTest
                .opening(ANY_OPENING_KEY)
                .relay(novel(FIRST_NOVEL_KEY, ANY_OPENING_KEY))
                .buildForRelay();

        prevParentKey = sut.getParentBy(novelBoard);
        novelBoard.relay(novel2(SECOND_NOVEL_KEY2, prevParentKey));

        assertThat(prevParentKey).isEqualTo(FIRST_NOVEL_KEY);
    }


    @Test
    void fork_될_때_parentNovelId는_직전_relayNovel의_parentNovelId_다() {
        NovelBoard novelBoard = novelBoardBuilderForTest
                .opening(ANY_OPENING_KEY)
                .relay(novel(FIRST_NOVEL_KEY, ANY_OPENING_KEY))
                .buildForRelay();

        prevParentKey = sut.getParentForForkBy(novelBoard);
        novelBoard.fork(novel2(SECOND_NOVEL_KEY2, prevParentKey));

        assertThat(prevParentKey).isEqualTo(ANY_OPENING_KEY);
    }

    private Novel novel(RelayNovelKey id, NovelKey parentId) {
        return Novel.of(id, parentId, WriterId.of("any"), Title.of("any"), Content.of("value"));
    }

    private Novel novel2(RelayNovelKey id, NovelKey parentId) {
        return Novel.of(id, parentId, WriterId.of("any2"), Title.of("any2"), Content.of("value2"));
    }
}