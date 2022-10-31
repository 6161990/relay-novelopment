package com.yoon.relayNovelopment.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class NovelNodeTest {

    @Test
    void create_novelNode() {
        Novel novel = Novel.of(NovelId.of("id"), WriterId.of("writer"), Title.of("bang"), new Contents(), new Props());

        NovelNode node = new NovelNode(NovelNodeId.of("nodeId1"), novel);

        assertThat(node.getNovels().size()).isEqualTo(1);
    }

    @Test
    void add_novel_at_novelNode() {
        Novel novel = Novel.of(NovelId.of("id"), WriterId.of("writer"), Title.of("bang"), new Contents(), new Props());
        NovelNode node = new NovelNode(NovelNodeId.of("nodeId1"), novel);
        assertThat(node.getNovels().size()).isEqualTo(1);

        Novel novel2 = Novel.of(NovelId.of("id2"), WriterId.of("writer2"), Title.of("bang2"), new Contents(), new Props());
        node.register(novel2);

        assertThat(node.getNovels().size()).isEqualTo(2);
    }

    @Test
    void writer는_하나의_novelNode에_최초한번만_novel을_등록할_수_있다() {

    }

    @Test
    void 하나의_novelNode에_제목이_겹칠_수_없다() {

    }

    @Test
    void 하나의_novelNode는_중복된_Novel이_등록될_수_없다() {
        
    }

    @Test
    void name() {
    }

    // TODO : 유저에 따라 node 를 추가시킬 수 있다. 등급이 높으면 이미 10개의 노드가 달려있는 노드 노벨에 추가할 수 있다.
}