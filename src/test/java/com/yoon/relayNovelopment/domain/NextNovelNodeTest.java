package com.yoon.relayNovelopment.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class NextNovelNodeTest {

    @Test
    void create_novelNode() {
        NextNovel novel = NextNovel.of(NextNovelId.of("id"), WriterId.of("writer"), Title.of("bang"), new Contents(), new Props());

        NovelNode node = new NovelNode(NovelNodeId.of("nodeId1"),
                OpeningNovel.of(OpeningNovelId.of("openingNovelId"),
                        WriterId.of("writer"), Title.of("bang"),
                        Content.of(ContentId.of("contentId"), "content"), new Props()),
                novel);

        assertThat(node.getNovelSize()).isEqualTo(1);
    }

    @Test
    void add_novel_at_novelNode() {
        NextNovel novel = NextNovel.of(NextNovelId.of("id"), WriterId.of("writer"), Title.of("bang"), new Contents(), new Props());
        NovelNode node = new NovelNode(NovelNodeId.of("nodeId1"),
                                       OpeningNovel.of(OpeningNovelId.of("openingNovelId"),
                                                        WriterId.of("writer"), Title.of("bang"),
                                                        Content.of(ContentId.of("contentId"), "content"), new Props()),
                                       novel);
        assertThat(node.getNovels().size()).isEqualTo(1);

        NextNovel novel2 = NextNovel.of(NextNovelId.of("id2"), WriterId.of("writer2"), Title.of("bang2"), new Contents(), new Props());
        node.publish(novel2);

        assertThat(node.getNovelSize()).isEqualTo(2);
    }

    @Test
    void writer는_하나의_novelNode에_중복하여_등록할_수_없다() {
        NextNovel novel = NextNovel.of(NextNovelId.of("id"), WriterId.of("writer"), Title.of("bang"), new Contents(), new Props());
        NovelNode node = new NovelNode(NovelNodeId.of("nodeId1"),
                OpeningNovel.of(OpeningNovelId.of("openingNovelId"),
                        WriterId.of("writer"), Title.of("bang"),
                        Content.of(ContentId.of("contentId"), "content"), new Props()),
                novel);
        assertThat(node.getNovels().size()).isEqualTo(1);

        NextNovel novel2 = NextNovel.of(NextNovelId.of("id2"), WriterId.of("writer"), Title.of("bang2"), new Contents(), new Props());

        assertThatThrownBy(()-> node.publish(novel2))
                .isInstanceOf(NovelNodeException.class)
                .hasMessage(String.format("Already exist the writer. WriterId %s, NovelNodeId %s", WriterId.of("writer"), NovelNodeId.of("nodeId1")));
    }

    @Test
    void 하나의_novelNode에_제목이_겹칠_수_없다() {
        NextNovel novel = NextNovel.of(NextNovelId.of("id"), WriterId.of("writer"), Title.of("bang"), new Contents(), new Props());
        NovelNode node = new NovelNode(NovelNodeId.of("nodeId1"),
                OpeningNovel.of(OpeningNovelId.of("openingNovelId"),
                        WriterId.of("writer"), Title.of("bang"),
                        Content.of(ContentId.of("contentId"), "content"), new Props()),
                novel);
        assertThat(node.getNovels().size()).isEqualTo(1);

        NextNovel novel2 = NextNovel.of(NextNovelId.of("id2"), WriterId.of("writer2"), Title.of("bang"), new Contents(), new Props());

        assertThatThrownBy(()-> node.publish(novel2))
                .isInstanceOf(NovelNodeException.class)
                .hasMessage(String.format("Already exist the title. WriterId %s, NovelNodeId %s", WriterId.of("writer2"), NovelNodeId.of("nodeId1")));
    }

    @Test
    void 하나의_novelNode는_중복된_Novel이_등록될_수_없다() {
        // FIXME 질문 novelId 가 같다면 도메인까지 못오게 해야하는 것 아님? -> 어플리케이션에서 할 일.. ?
        NextNovel novel = NextNovel.of(NextNovelId.of("id"), WriterId.of("writer"), Title.of("bang"), new Contents(), new Props());
        NovelNode node = new NovelNode(NovelNodeId.of("nodeId1"),
                OpeningNovel.of(OpeningNovelId.of("openingNovelId"),
                        WriterId.of("writer"), Title.of("bang"),
                        Content.of(ContentId.of("contentId"), "content"), new Props()),
                novel);
        assertThat(node.getNovelSize()).isEqualTo(1);

        NextNovel novel2 = NextNovel.of(NextNovelId.of("id"), WriterId.of("writer2"), Title.of("bang2"), new Contents(), new Props());

        assertThatThrownBy(()-> node.publish(novel2))
                .isInstanceOf(NovelNodeException.class)
                .hasMessage(String.format("Already exist the novel. NovelId %s, NovelNodeId %s", NextNovelId.of("id"), NovelNodeId.of("nodeId1")));
    }


    // TODO : 유저에 따라 node 를 추가시킬 수 있다. 등급이 높으면 이미 10개의 노드가 달려있는 노드 노벨에 추가할 수 있다.
    // FIXME : 노벨상.................. 띵언 노벨 판타지상 노벨 환희상 등등
}