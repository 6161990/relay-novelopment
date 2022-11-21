package com.yoon.relayNovelopment.application;

import com.yoon.relayNovelopment.domain.*;
import org.junit.jupiter.api.Test;

import static com.yoon.relayNovelopment.domain.NovelBoardBuilder.*;
import static org.assertj.core.api.Assertions.assertThat;

class ParentFinderTest {

    @Test
    void 최초_novelBoard_생성시() {
        ParentFinder sut = new ParentFinder();
        NovelBoard novelBoard = NovelBoardBuilder.builder()
                .id("id")
                .opening(openingId("id"), writerId("writer"),
                        title("bang"), content("contentId", "content"))
                .build();

        NovelId parentId = sut.getParentBy(novelBoard);

        assertThat(parentId).isEqualTo(openingId("id"));
    }

    @Test
    void 최초_relay_될_때() {
        ParentFinder sut = new ParentFinder();
        NovelBoard novelBoard = NovelBoardBuilder.builder()
                .id("id")
                .opening(openingId("id"), writerId("writer"),
                        title("bang"), content("contentId", "content"))
                .build();
        novelBoard.relay(novel(relayId("id2"), openingId("id"), writerId("writer2"), title("bang2")));

        NovelId parentId = sut.getParentBy(novelBoard);

        assertThat(parentId).isEqualTo(relayId("id2"));
    }

    @Test
    void 두번째_relay_될_때() {
        ParentFinder sut = new ParentFinder();
        NovelBoard novelBoard = NovelBoardBuilder.builder()
                .id("id")
                .opening(openingId("id"), writerId("writer"),
                        title("bang"), content("contentId", "content"))
                .build();
        novelBoard.relay(novel(relayId("id2"), openingId("id"), writerId("writer2"), title("bang2")));

        novelBoard.relay(novel(relayId("id3"), parentId("id2"), writerId("writer3"), title("bang3")));

        NovelId parentId = sut.getParentBy(novelBoard);

        assertThat(parentId).isEqualTo(relayId("id3"));
    }

    @Test
    void fork_될_때() {
        ParentFinder sut = new ParentFinder();
        NovelBoard novelBoard = NovelBoardBuilder.builder()
                .id("id")
                .opening(openingId("id"), writerId("writer"),
                        title("bang"), content("contentId", "content"))
                .build();
        novelBoard.relay(novel(relayId("id2"), openingId("id"), writerId("writer2"), title("bang2")));

        NovelId parentId = sut.getParentByFork(novelBoard);

        assertThat(parentId).isEqualTo(openingId("id"));
    }

    @Test
    void 부모novelId가_무엇인지를_찾아야한다() { // FIXME 바깥 레이어의 test 같다.
        // 혹은 연결된 이전 노드가 무엇인지 찾아야한다 openingId ? parentId ---------- HERE !!!!!!!!!!!

    }

    private Novel novel(RelayNovelId id, NovelId parentId, WriterId writerId, Title title) {
        return Novel.of(id, parentId, writerId, title, Content.of(ContentId.of("conId"), "value"), new Props());
    }
}