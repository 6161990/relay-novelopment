package com.yoon.relayNovelopment.domain;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class NovelNode {
    private List<Novel> novels;

    public NovelNode() {
        this.init();
    }

    private void init() {
        this.novels = new ArrayList<>();
    }

    public void add(Novel novel) {
        this.novels.add(novel);
    }
}
