package com.yoon.relayNovelopment.domain;

import org.junit.jupiter.api.Test;

import static com.yoon.relayNovelopment.domain.NovelStageBuilder.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class NovelBoardTest {

    private final int NODE_DEPTH = 5;

    @Test
    void create_novelStage() {
        NovelBoard novelStage = NovelStageBuilder.builder()
                .id("id")
                .opening(openingId("id"), writerId("writer"),
                         title("bang"), content("contentId", "content"))
                .build();

        assertThat(novelStage.getNovelSize()).isEqualTo(0);
    }

    @Test
    void add_novel() {
        NovelBoard novelStage = NovelStageBuilder.builder()
                .id("id")
                .opening(openingId("id"), writerId("writer"),
                        title("bang"), content("contentId", "content"))
                .stage(NODE_DEPTH)
                .build();

        Novel novel2 = Novel.of(NovelId.of("id2"), WriterId.of("writer2"), Title.of("bang2"), new Contents(), new Props());
        novelStage.relay(novel2);

        Novel novel3 = Novel.of(NovelId.of("id3"), WriterId.of("writer3"), Title.of("bang3"), new Contents(), new Props());
        novelStage.relay(novel3);

        assertThat(novelStage.getNovelSize()).isEqualTo(2);
    }

    @Test
    void writer는_하나의_novelStage_에_중복하여_등록할_수_없다() {
        NovelBoard stage = NovelStageBuilder.builder()
                .id("id")
                .opening(openingId("id"), writerId("writer"),
                        title("bang"), content("contentId", "content"))
                .stage(NODE_DEPTH)
                .build();

        Novel novel = Novel.of(NovelId.of("id2"), WriterId.of("writer"), Title.of("bang2"), new Contents(), new Props());
        stage.relay(novel);

        Novel novel2 = Novel.of(NovelId.of("id3"), WriterId.of("writer"), Title.of("bang3"), new Contents(), new Props());

        assertThatThrownBy(()-> stage.relay(novel2))
                .isInstanceOf(NovelNodeException.class)
                .hasMessage(String.format("Already exist the writer. WriterId %s, NovelNodeId %s", WriterId.of("writer"), NovelStageId.of("id")));

    }

    @Test
    void 하나의_novelStage_에_제목이_중복될_수_없다() {
        NovelBoard stage = NovelStageBuilder.builder()
                .id("id")
                .opening(openingId("id"), writerId("writer"),
                        title("bang1"), content("contentId", "content"))
                .stage(NODE_DEPTH)
                .build();

        Novel novel = Novel.of(NovelId.of("id2"), WriterId.of("writer2"), Title.of("bang2"), new Contents(), new Props());
        stage.relay(novel);

        Novel novel2 = Novel.of(NovelId.of("id3"), WriterId.of("writer3"), Title.of("bang2"), new Contents(), new Props());

        assertThatThrownBy(()-> stage.relay(novel2))
                .isInstanceOf(NovelNodeException.class)
                .hasMessage(String.format("Already exist the title. WriterId %s, NovelNodeId %s", WriterId.of("writer3"), NovelStageId.of("id")));
    }

    // TODO : 유저에 따라 Stage 를 추가시킬 수 있다. 등급이 높으면 이미 10개의 Stage 가 달려있는 novelBoard 에 추가할 수 있다.
    // FIXME : 노벨상.................. 띵언 노벨 판타지상 노벨 환희상 등등
}