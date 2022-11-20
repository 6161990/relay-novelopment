package com.yoon.relayNovelopment.domain;

import org.junit.jupiter.api.Test;

import static com.yoon.relayNovelopment.domain.NovelBoardBuilder.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class NovelBoardTest {

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
                        title("bang"), content("contentId", "content")).build();
        novelStage.relay(novel(relayId("id2"), openingId("id"), writerId("writer2"), title("bang2")));

        novelStage.relay(novel(relayId("id3"), parentId("id2"), writerId("writer3"), title("bang3")));

        assertThat(novelStage.getNovelSize()).isEqualTo(2);
    }

    @Test
    void 부모novelId가_무엇인지를_찾아야한다() { // FIXME 바깥 레이어의 test 같다.
            // 혹은 연결된 이전 노드가 무엇인지 찾아야한다 openingId ? parentId ---------- HERE !!!!!!!!!!!
        Novel novel2 = novel(RelayNovelId.of("id2"), OpeningId.of("id"), WriterId.of("writer2"), Title.of("bang2"));
    }

    @Test
    void when_already_closed_add_relay() {

    }

    @Test
    void add_fork() {
        NovelBoard novelStage = NovelBoardBuilder.builder()
                .id("id")
                .opening(openingId("id"), writerId("writer"),
                        title("bang"), content("contentId", "content")).build();
        novelStage.relay(novel(relayId("id2"), openingId("id"), writerId("writer2"), title("bang2")));

        novelStage.fork(novel(relayId("id3"), openingId("id"), writerId("writer3"), title("bang4")));

        assertThat(novelStage.getNovelSize()).isEqualTo(2);
        assertThat(novelStage.getSameParentSizeBy(openingId("id"))).isEqualTo(2);
    }

    @Test
    void when_not_exist_same_parentNovel_add_fork() {
        NovelBoard novelStage = NovelBoardBuilder.builder()
                .id("id")
                .opening(openingId("id"), writerId("writer"),
                        title("bang"), content("contentId", "content")).build();
        novelStage.relay(novel(relayId("id2"), openingId("id"), writerId("writer2"), title("bang2")));

        assertThatThrownBy(()->novelStage.fork(novel(relayId("id3"), openingId("id2"), writerId("writer3"), title("bang4"))))
                .isInstanceOf(NovelNodeException.class)
                .hasMessage("Not Exist Same Parent Novel.");
    }

    @Test
    void writer는_하나의_novelStage_에_중복하여_등록할_수_없다() {
        NovelBoard novelStage = NovelBoardBuilder.builder()
                .id("id")
                .opening(openingId("id"), writerId("writer"),
                        title("bang"), content("contentId", "content")).build();
        novelStage.relay(novel(relayId("id2"), openingId("id"), writerId("writer2"), title("bang2")));

        assertThatThrownBy(()-> novelStage.relay(novel(relayId("id3"), parentId("id2"), writerId("writer2"), title("bang2"))))
                .isInstanceOf(NovelNodeException.class)
                .hasMessage(String.format("Already exist the writer. WriterId %s, NovelBoardId %s", WriterId.of("writer2"), NovelBoardId.of("id")));

    }

    @Test
    void 하나의_novelStage_에_제목이_중복될_수_없다() {
        NovelBoard novelStage = NovelBoardBuilder.builder()
                .id("id")
                .opening(openingId("id"), writerId("writer"),
                        title("bang"), content("contentId", "content")).build();
        novelStage.relay(novel(relayId("id2"), openingId("id"), writerId("writer2"), title("bang2")));

        assertThatThrownBy(()-> novelStage.relay(novel(relayId("id3"), parentId("id2"), writerId("writer3"), title("bang2"))))
                .isInstanceOf(NovelNodeException.class)
                .hasMessage(String.format("Already exist the title. WriterId %s, NovelBoardId %s", WriterId.of("writer3"), NovelBoardId.of("id")));
    }

    private Novel novel(RelayNovelId id, NovelId parentId, WriterId writerId, Title title) {
        return Novel.of(id, parentId, writerId, title, Content.of(ContentId.of("conId"), "value"), new Props());
    }

}