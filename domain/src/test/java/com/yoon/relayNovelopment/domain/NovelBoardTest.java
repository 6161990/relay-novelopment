package com.yoon.relayNovelopment.domain;

import org.junit.jupiter.api.DisplayName;
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
                         title("bang"), content("content"))
                .build();

        assertThat(novelStage.getNovelSize()).isEqualTo(0);
    }

    @Test
    void add_novel() {
        NovelBoard novelStage = NovelBoardBuilder.builder()
                .id("id")
                .opening(openingId("id"), writerId("writer"),
                        title("bang"), content("content")).build();
        novelStage.relay(novel(relayId("id2"), openingId("id"), writerId("writer2"), title("bang2")));

        novelStage.relay(novel(relayId("id3"), parentId("id2"), writerId("writer3"), title("bang3")));

        assertThat(novelStage.getNovelSize()).isEqualTo(2);
    }

    @Test
    void when_already_closed_add_relay() {

    }

    @Test
    void add_fork() {
        NovelBoard novelStage = NovelBoardBuilder.builder()
                .id("id")
                .opening(openingId("id"), writerId("writer"),
                        title("bang"), content("content")).build();
        novelStage.relay(novel(relayId("id2"), openingId("id"), writerId("writer2"), title("bang2")));

        novelStage.fork(novel(relayId("id3"), openingId("id"), writerId("writer3"), title("bang4")));

        assertThat(novelStage.getNovelSize()).isEqualTo(2);
        assertThat(novelStage.getSameParentSizeBy(openingId("id"))).isEqualTo(2);
    }

    @DisplayName("fork->relay")
    @Test
    void relay_at_fork() {
        NovelBoard novelStage = NovelBoardBuilder.builder()
                .id("id")
                .opening(openingId("id"), writerId("writer"),
                        title("bang"), content("content")).build();
        novelStage.relay(novel(relayId("id2"), openingId("id"), writerId("writer2"), title("bang2")));
        novelStage.fork(novel(relayId("id3"), openingId("id"), writerId("writer3"), title("bang4")));

        novelStage.relay(novel(relayId("id4"), parentId("id3"), writerId("writer4"), title("bang5")));

        assertThat(novelStage.getNovelSize()).isEqualTo(3);
    }

    @DisplayName("relay->fork->relay->fork")
    @Test
    void fork_at_relay_after_fork() {
        NovelBoard novelStage = NovelBoardBuilder.builder()
                .id("id")
                .opening(openingId("id"), writerId("writer"),
                        title("bang"), content("content")).build();
        novelStage.relay(novel(relayId("id2"), openingId("id"), writerId("writer2"), title("bang2")));
        novelStage.fork(novel(relayId("id3"), openingId("id"), writerId("writer3"), title("bang4")));
        novelStage.relay(novel(relayId("id4"), parentId("id3"), writerId("writer4"), title("bang5")));
        novelStage.fork(novel(relayId("id5"), parentId("id3"), writerId("writer5"), title("bang6")));

        assertThat(novelStage.getNovelSize()).isEqualTo(4);
    }

    @Test
    void when_not_exist_same_parentNovel_add_fork() {
        NovelBoard novelStage = NovelBoardBuilder.builder()
                .id("id")
                .opening(openingId("id"), writerId("writer"),
                        title("bang"), content("content")).build();
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
                        title("bang"), content("content")).build();
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
                        title("bang"), content("content")).build();
        novelStage.relay(novel(relayId("id2"), openingId("id"), writerId("writer2"), title("bang2")));

        assertThatThrownBy(()-> novelStage.relay(novel(relayId("id3"), parentId("id2"), writerId("writer3"), title("bang2"))))
                .isInstanceOf(NovelNodeException.class)
                .hasMessage(String.format("Already exist the title. WriterId %s, NovelBoardId %s", WriterId.of("writer3"), NovelBoardId.of("id")));
    }

    private Novel novel(RelayNovelKey id, NovelKey parentId, WriterId writerId, Title title) {
        return Novel.of(id, parentId, writerId, title, Content.of("value"));
    }

}