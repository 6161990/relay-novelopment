package com.yoon.relayNovelopment.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.yoon.relayNovelopment.domain.NovelBoardBuilderForTest.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class NovelBoardTest {

    @Test
    void create_novelBoard() {
        NovelBoard novelBoard = NovelBoardBuilderForTest.builder()
                .id("id")
                .opening(openingKey("openKey"), writerId("writer"),
                        title("bang"), content("content"))
                .build();

        assertThat(novelBoard.getOpening().getOpeningKey()).isEqualTo(OpeningKey.of("openKey"));
    }

    @Test
    void add_novel() {
        NovelBoard novelBoard = NovelBoardBuilderForTest.builder()
                .id("id")
                .opening(openingKey("id"), writerId("writer"), title("bang"), content("content"))
                .relay(novel(relayKey("id2"), openingKey("id"), writerId("writer2"), title("bang2")))
                .buildForRelay();

        novelBoard.relay(novel(relayKey("id3"), parentKey("id2"), writerId("writer3"), title("bang3")));

        assertThat(novelBoard.getNovelSize()).isEqualTo(2);
    }

    @Test
    void when_already_closed_add_relay() {
            // TODO 소설이 완성되는 것은 언제인가.
    }

    @Test
    void add_fork() {
        NovelBoard novelBoard = NovelBoardBuilderForTest.builder()
                .id("id")
                .opening(openingKey("id"), writerId("writer"), title("bang"), content("content"))
                .relay(novel(relayKey("id2"), openingKey("id"), writerId("writer2"), title("bang2")))
                .buildForRelay();

        novelBoard.fork(novel(relayKey("id3"), openingKey("id"), writerId("writer3"), title("bang4")));

        assertThat(novelBoard.getNovelSize()).isEqualTo(2);
        assertThat(novelBoard.getNovels().getSameParentSizeBy(openingKey("id"))).isEqualTo(2);
    }

    @DisplayName("relay->fork->relay")
    @Test
    void relay_at_fork() {
        NovelBoard novelBoard = NovelBoardBuilderForTest.builder()
                .id("id")
                .opening(openingKey("id"), writerId("writer"), title("bang"), content("content"))
                .relay(novel(relayKey("id2"), openingKey("id"), writerId("writer2"), title("bang2")))
                .buildForRelay();

        novelBoard.fork(novel(relayKey("id3"), openingKey("id"), writerId("writer3"), title("bang4")));
        novelBoard.relay(novel(relayKey("id4"), parentKey("id3"), writerId("writer4"), title("bang5")));

        assertThat(novelBoard.getNovelSize()).isEqualTo(3);
    }

    @DisplayName("relay->fork->relay->fork")
    @Test
    void fork_at_relay_after_fork() {
        NovelBoard novelBoard = NovelBoardBuilderForTest.builder()
                .id("id")
                .opening(openingKey("id"), writerId("writer"), title("bang"), content("content"))
                .relay(novel(relayKey("id2"), openingKey("id"), writerId("writer2"), title("bang2")))
                .buildForRelay();

        novelBoard.fork(novel(relayKey("id3"), openingKey("id"), writerId("writer3"), title("bang4")));
        novelBoard.relay(novel(relayKey("id4"), parentKey("id3"), writerId("writer4"), title("bang5")));
        novelBoard.fork(novel(relayKey("id5"), parentKey("id3"), writerId("writer5"), title("bang6")));

        assertThat(novelBoard.getNovelSize()).isEqualTo(4);
    }

    @Test
    void when_not_exist_relay_before_fork() {
        NovelBoard novelBoard = NovelBoardBuilderForTest.builder()
                .id("id")
                .opening(openingKey("id"), writerId("writer"), title("bang"), content("content"))
                .build();

        assertThatThrownBy(()->novelBoard.fork(novel(relayKey("id3"), openingKey("id"), writerId("writer3"), title("bang4"))))
                .isInstanceOf(NovelBoardException.class)
                .hasMessage("Novels is Empty.");
    }

    @Test
    void when_not_exist_same_parentNovel_add_fork() {
        NovelBoard novelBoard = NovelBoardBuilderForTest.builder()
                .id("id")
                .opening(openingKey("id"), writerId("writer"), title("bang"), content("content"))
                .relay(novel(relayKey("id2"), openingKey("id"), writerId("writer2"), title("bang2")))
                .buildForRelay();

        assertThatThrownBy(()->novelBoard.fork(novel(relayKey("id3"), openingKey("id2"), writerId("writer3"), title("bang4"))))
                .isInstanceOf(NovelBoardException.class)
                .hasMessage("Not Exist Same Parent Novel.");
    }

    @Test
    void writer는_하나의_novelBoard_에_중복하여_등록할_수_없다() {
        NovelBoard novelBoard = NovelBoardBuilderForTest.builder()
                .id("id")
                .opening(openingKey("id"), writerId("writer"), title("bang"), content("content"))
                .relay(novel(relayKey("id2"), openingKey("id"), writerId("writer2"), title("bang2")))
                .buildForRelay();

        assertThatThrownBy(()-> novelBoard.relay(novel(relayKey("id3"), parentKey("id2"), writerId("writer2"), title("bang2"))))
                .isInstanceOf(NovelBoardException.class)
                .hasMessageContaining("Already exist the writer.");

    }

    @Test
    void 하나의_novelBoard_에_제목이_중복될_수_없다() {
        NovelBoard novelBoard = NovelBoardBuilderForTest.builder()
                .id("id")
                .opening(openingKey("id"), writerId("writer"), title("bang"), content("content"))
                .relay(novel(relayKey("id2"), openingKey("id"), writerId("writer2"), title("bang2")))
                .buildForRelay();

        assertThatThrownBy(()-> novelBoard.relay(novel(relayKey("id3"), parentKey("id2"), writerId("writer3"), title("bang2"))))
                .isInstanceOf(NovelBoardException.class)
                .hasMessageContaining("Already exist the title.");
    }

    private Novel novel(RelayNovelKey id, NovelKey parentId, WriterId writerId, Title title) {
        return Novel.of(id, parentId, writerId, title, Content.of("value"));
    }

}