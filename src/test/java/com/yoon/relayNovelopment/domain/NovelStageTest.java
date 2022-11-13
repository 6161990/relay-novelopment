package com.yoon.relayNovelopment.domain;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.yoon.relayNovelopment.domain.NovelStageBuilder.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class NovelStageTest {

    private final int NODE_DEPTH = 5;

    @Test
    void create_novelStage() {
        NovelStage novelStage = NovelStageBuilder.builder()
                .id("id")
                .opening(openingId("id"), writerId("writer"),
                         title("bang"), content("contentId", "content"))
                .build();

        assertThat(novelStage.getNovelSize()).isEqualTo(0);
    }


    @Test
    void add_fork_at_novelStage() {
        NovelStage novelStage = NovelStageBuilder.builder()
                .id("id")
                .opening(openingId("id"), writerId("writer"),
                        title("bang"), content("contentId", "content"))
                .stage(NODE_DEPTH)
                .build();

        Fork fork2 = Fork.of(ForkId.of("id2"), WriterId.of("writer2"), Title.of("bang2"), new Contents(), new Props());
        novelStage.createRelay(fork2);

        Fork fork3 = Fork.of(ForkId.of("id3"), WriterId.of("writer3"), Title.of("bang3"), new Contents(), new Props());
        novelStage.createRelay(fork3);

        assertThat(novelStage.getNovelSize()).isEqualTo(2);
    }

    @Test
    void writer는_하나의_novelStage_에_중복하여_등록할_수_없다() {
        NovelStage stage = NovelStageBuilder.builder()
                .id("id")
                .opening(openingId("id"), writerId("writer"),
                        title("bang"), content("contentId", "content"))
                .stage(NODE_DEPTH)
                .build();

        Fork fork = Fork.of(ForkId.of("id2"), WriterId.of("writer"), Title.of("bang2"), new Contents(), new Props());
        assertThatThrownBy(()-> stage.createRelay(fork)) // FIXME : relayList 가 아니라 fork 를 전달해야한다.
                .isInstanceOf(NovelNodeException.class)
                .hasMessage(String.format("Already exist the writer. WriterId %s, NovelNodeId %s", WriterId.of("writer"), NovelStageId.of("id")));

    }

    @Test
    void 하나의_novelStage_에_제목이_중복될_수_없다() {
        NovelStage stage = NovelStageBuilder.builder()
                .id("id")
                .opening(openingId("id"), writerId("writer"),
                        title("bang"), content("contentId", "content"))
                .stage(NODE_DEPTH)
                .build();

        Fork fork = Fork.of(ForkId.of("id2"), WriterId.of("writer2"), Title.of("bang"), new Contents(), new Props());

        assertThatThrownBy(()-> stage.createRelay(fork)) // FIXME : relayList 가 아니라 fork 를 전달해야한다.
                .isInstanceOf(NovelNodeException.class)
                .hasMessage(String.format("Already exist the title. WriterId %s, NovelNodeId %s", WriterId.of("writer2"), NovelStageId.of("id")));
    }

    @Disabled
    @Test
    void 하나의_novelNode는_중복된_Novel이_등록될_수_없다() {
        // FIXME 질문 novelId 가 같다면 도메인까지 못오게 해야하는 것 아님? -> 어플리케이션에서 할 일.. ?
        NovelStage node = NovelStageBuilder.builder()
                .id("id")
                .opening(openingId("id"), writerId("writer"),
                        title("bang"), content("contentId", "content"))
                .stage(NODE_DEPTH)
                .build();

        Fork fork = Fork.of(ForkId.of("id"), WriterId.of("writer2"), Title.of("bang2"), new Contents(), new Props());

        assertThatThrownBy(()-> node.createRelay(fork))
                .isInstanceOf(NovelNodeException.class)
                .hasMessage(String.format("Already exist the novel. NovelId %s, NovelNodeId %s", ForkId.of("id"), NovelStageId.of("id")));
    }

    // TODO : 유저에 따라 node 를 추가시킬 수 있다. 등급이 높으면 이미 10개의 노드가 달려있는 노드 노벨에 추가할 수 있다.
    // FIXME : 노벨상.................. 띵언 노벨 판타지상 노벨 환희상 등등
}