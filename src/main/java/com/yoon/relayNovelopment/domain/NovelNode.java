package com.yoon.relayNovelopment.domain;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class NovelNode {
    private List<Novel> novels;

    public NovelNode(Novel novel) {
        this.init(novel);
    }

    private void init(Novel novel) {
        this.novels = new ArrayList<>();
        this.add(novel);
    }

    public void add(Novel novel) {
        this.novels.add(novel);
    }
}
