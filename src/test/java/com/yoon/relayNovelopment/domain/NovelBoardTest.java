package com.yoon.relayNovelopment.domain;

import org.junit.jupiter.api.Test;

import static com.yoon.relayNovelopment.domain.NovelBoardBuilder.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class NovelBoardTest {

    private final int MAX_RELAY_SIZE_5 = 5;

    @Test
    void create_novelStage() {
        NovelBoard novelStage = NovelBoardBuilder.builder()
                .id("id")
                .opening(openingId("id"), writerId("writer"),
                         title("bang"), content("contentId", "content"))
                .build();

        assertThat(novelStage.getNovelSize()).isEqualTo(0);
    }

    @Test
    void add_novel() {
        NovelBoard novelStage = NovelBoardBuilder.builder()
                .id("id")
                .opening(openingId("id"), writerId("writer"),
                        title("bang"), content("contentId", "content"))
                .maxRelay(MAX_RELAY_SIZE_5).build();

        novelStage.relay(Novel.of(relayId("id2"), openingId("id"), writerId("writer2"), title("bang2")));

        novelStage.relay(Novel.of(relayId("id3"), parentId("id2"), writerId("writer3"), title("bang3")));

        assertThat(novelStage.getNovelSize()).isEqualTo(2);
    }

    @Test
    void 부모novelId가_무엇인지를_찾아야한다() {
            // 혹은 연결된 이전 노드가 무엇인지 찾아야한다 ---------- HERE !!!!!!!!!!!
        Novel novel2 = Novel.of(RelayNovelId.of("id2"), OpeningId.of("id"), WriterId.of("writer2"), Title.of("bang2"));
    }

    @Test
    void add_fork() {
        // 부모 novelId가 일치한 형제노드가 반드시 존재한다.

    }

    @Test
    void when_exceed_maxRelayCount_add_relay() {

    }

    @Test
    void writer는_하나의_novelStage_에_중복하여_등록할_수_없다() {
        NovelBoard novelStage = NovelBoardBuilder.builder()
                .id("id")
                .opening(openingId("id"), writerId("writer"),
                        title("bang"), content("contentId", "content"))
                .maxRelay(MAX_RELAY_SIZE_5).build();

        novelStage.relay(Novel.of(relayId("id2"), openingId("id"), writerId("writer2"), title("bang2")));

        assertThatThrownBy(()-> novelStage.relay(Novel.of(relayId("id3"), parentId("id2"), writerId("writer2"), title("bang2"))))
                .isInstanceOf(NovelNodeException.class)
                .hasMessage(String.format("Already exist the writer. WriterId %s, NovelBoardId %s", WriterId.of("writer2"), NovelBoardId.of("id")));

    }

    @Test
    void 하나의_novelStage_에_제목이_중복될_수_없다() {
        NovelBoard novelStage = NovelBoardBuilder.builder()
                .id("id")
                .opening(openingId("id"), writerId("writer"),
                        title("bang"), content("contentId", "content"))
                .maxRelay(MAX_RELAY_SIZE_5).build();

        novelStage.relay(Novel.of(relayId("id2"), openingId("id"), writerId("writer2"), title("bang2")));

        assertThatThrownBy(()-> novelStage.relay(Novel.of(relayId("id3"), parentId("id2"), writerId("writer3"), title("bang2"))))
                .isInstanceOf(NovelNodeException.class)
                .hasMessage(String.format("Already exist the title. WriterId %s, NovelBoardId %s", WriterId.of("writer3"), NovelBoardId.of("id")));
    }

}