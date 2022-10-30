package com.yoon.relayNovelopment.domain;

import com.yoon.relayNovelopment.domain.*;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class NovelNodeTest {

    @Test
    void novelNode_에_novel_을_추가한다() {
        NovelNode node = new NovelNode();

        Props props = new Props();
        node.add(Novel.of(NovelId.of("id"), WriterId.of("writer"), Title.of("bang"), new Contents() ,props));

        assertThat(node.getNovels().size()).isEqualTo(1);
    }
}