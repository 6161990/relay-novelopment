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
        NovelBoardBuilder novelStage = NovelBoardBuilder.builder()
                .id("id")
                .opening(openingId("id"), writerId("writer"),
                        title("bang"), content("contentId", "content"))
                .relay(relayId("id2"), openingId("id"), writerId("writer2"), title("bang2"))
                .maxRelay(MAX_RELAY_SIZE_5);

        novelStage.relay(relayId("id3"), relayId("id2"), writerId("writer2"), title("bang2"));

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
    void writer는_하나의_novelStage_에_중복하여_등록할_수_없다() {
        NovelBoard stage = NovelBoardBuilder.builder()
                .id("id")
                .opening(openingId("id"), writerId("writer"),
                        title("bang"), content("contentId", "content"))
                .maxRelay(MAX_RELAY_SIZE_5)
                .build();

        Novel novel = Novel.of(RelayNovelId.of("id2"), OpeningId.of("id"), WriterId.of("writer"), Title.of("bang2"));
        stage.relay(novel);

        Novel novel2 = Novel.of(RelayNovelId.of("id3"), RelayNovelId.of("id2"), WriterId.of("writer"), Title.of("bang3"));

        assertThatThrownBy(()-> stage.relay(novel2))
                .isInstanceOf(NovelNodeException.class)
                .hasMessage(String.format("Already exist the writer. WriterId %s, NovelNodeId %s", WriterId.of("writer"), NovelStageId.of("id")));

    }

    @Test
    void 하나의_novelStage_에_제목이_중복될_수_없다() {
        NovelBoard stage = NovelBoardBuilder.builder()
                .id("id")
                .opening(openingId("id"), writerId("writer"),
                        title("bang1"), content("contentId", "content"))
                .maxRelay(MAX_RELAY_SIZE_5)
                .build();

        Novel novel = Novel.of(RelayNovelId.of("id2"), OpeningId.of("id"), WriterId.of("writer2"), Title.of("bang2"));
        stage.relay(novel);

        Novel novel2 = Novel.of(RelayNovelId.of("id3"), RelayNovelId.of("id2"), WriterId.of("writer3"), Title.of("bang2"));

        assertThatThrownBy(()-> stage.relay(novel2))
                .isInstanceOf(NovelNodeException.class)
                .hasMessage(String.format("Already exist the title. WriterId %s, NovelNodeId %s", WriterId.of("writer3"), NovelStageId.of("id")));
    }

}