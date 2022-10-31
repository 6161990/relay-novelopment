package com.yoon.relayNovelopment.domain;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class NovelNode {
    private NovelNodeId id;
    private List<Novel> novels;

    public NovelNode(NovelNodeId id, Novel novel) {
        this.id = id;
        this.init(novel);
    }

    private void init(Novel novel) {
        this.novels = new ArrayList<>();
        this.register(novel);
    }

    public void register(Novel novel) {
        this.novels.add(novel);
    }
}
