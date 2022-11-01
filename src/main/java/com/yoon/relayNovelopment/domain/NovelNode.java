package com.yoon.relayNovelopment.domain;

import lombok.Getter;
import org.valid4j.Validation;

import java.util.ArrayList;
import java.util.List;

import static org.valid4j.Validation.validate;

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
        validate(novels.stream().noneMatch(n->n.getWriter().getId().equals(novel.getWriter().getId())),
                new NovelNodeException(String.format("Already exist the writer. WriterId %s, NovelNodeId %s", novel.getWriter(), id)));
        validate(novels.stream().noneMatch(n->n.getTitle().equals(novel.getTitle())),
                new NovelNodeException(String.format("Already exist the title. WriterId %s, NovelNodeId %s", novel.getWriter(), id)));
        validate(novels.stream().noneMatch(n->n.getId().equals(novel.getId())),
                new NovelNodeException(String.format("Already exist the novel. NovelId %s, NovelNodeId %s", novel.getId(), id)));

        this.novels.add(novel);
    }
}
