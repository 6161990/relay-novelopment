package com.yoon.relayNovelopment.domain;

import org.junit.jupiter.api.Test;

import static com.yoon.relayNovelopment.domain.NovelBuilder.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class NovelTest {

    private final int NODE_DEPTH = 5;

    @Test
    void create_novelNode() {
        Novel novel = NovelBuilder.builder()
                .id("id")
                .opening(openingId("id"), writerId("writer"),
                         title("bang"), content("contentId", "content"))
                .build();

        Fork fork = Fork.of(ForkId.of("nextId"),
                                WriterId.of("writer2"),
                                Title.of("bang2"),
                                new Contents(), new Props());
        novel.publish(fork);

        assertThat(novel.getNovelSize()).isEqualTo(2); // openingNovel 을 list 에 추가시켜야할까?
    }


    @Test
    void add_novel_at_novelNode() {
        Novel node = NovelBuilder.builder()
                .id("id")
                .opening(openingId("id"), writerId("writer"),
                        title("bang"), content("contentId", "content"))
                .stage(NODE_DEPTH)
                .build();

        Fork novel2 = Fork.of(ForkId.of("id2"), WriterId.of("writer2"), Title.of("bang2"), new Contents(), new Props());
        node.publish(novel2);

        Fork novel3 = Fork.of(ForkId.of("id3"), WriterId.of("writer3"), Title.of("bang3"), new Contents(), new Props());
        node.publish(novel3);

        assertThat(node.getNovelSize()).isEqualTo(3);
    }

    @Test
    void writer는_하나의_novelNode에_중복하여_등록할_수_없다() {
        Novel node = NovelBuilder.builder()
                .id("id")
                .opening(openingId("id"), writerId("writer"),
                        title("bang"), content("contentId", "content"))
                .stage(NODE_DEPTH)
                .build();

        Fork novel2 = Fork.of(ForkId.of("id2"), WriterId.of("writer"), Title.of("bang2"), new Contents(), new Props());

        assertThatThrownBy(()-> node.publish(novel2))
                .isInstanceOf(NovelNodeException.class)
                .hasMessage(String.format("Already exist the writer. WriterId %s, NovelNodeId %s", WriterId.of("writer"), NovelNodeId.of("id")));
    }

    @Test
    void 하나의_novelNode에_제목이_겹칠_수_없다() {
        Novel node = NovelBuilder.builder()
                .id("id")
                .opening(openingId("id"), writerId("writer"),
                        title("bang"), content("contentId", "content"))
                .stage(NODE_DEPTH)
                .build();

        Fork novel2 = Fork.of(ForkId.of("id2"), WriterId.of("writer2"), Title.of("bang"), new Contents(), new Props());

        assertThatThrownBy(()-> node.publish(novel2))
                .isInstanceOf(NovelNodeException.class)
                .hasMessage(String.format("Already exist the title. WriterId %s, NovelNodeId %s", WriterId.of("writer2"), NovelNodeId.of("id")));
    }

    @Test
    void 하나의_novelNode는_중복된_Novel이_등록될_수_없다() {
        // FIXME 질문 novelId 가 같다면 도메인까지 못오게 해야하는 것 아님? -> 어플리케이션에서 할 일.. ?
        Novel node = NovelBuilder.builder()
                .id("id")
                .opening(openingId("id"), writerId("writer"),
                        title("bang"), content("contentId", "content"))
                .stage(NODE_DEPTH)
                .build();

        Fork novel2 = Fork.of(ForkId.of("id"), WriterId.of("writer2"), Title.of("bang2"), new Contents(), new Props());

        assertThatThrownBy(()-> node.publish(novel2))
                .isInstanceOf(NovelNodeException.class)
                .hasMessage(String.format("Already exist the novel. NovelId %s, NovelNodeId %s", ForkId.of("id"), NovelNodeId.of("id")));
    }

    // TODO : 유저에 따라 node 를 추가시킬 수 있다. 등급이 높으면 이미 10개의 노드가 달려있는 노드 노벨에 추가할 수 있다.
    // FIXME : 노벨상.................. 띵언 노벨 판타지상 노벨 환희상 등등
}