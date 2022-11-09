package com.yoon.relayNovelopment.domain;

import lombok.Getter;
import lombok.ToString;
import org.valid4j.Validation;

import java.util.ArrayList;
import java.util.List;

@Getter
@ToString
public class NovelNode {
    private final NovelNodeId id;
    private final Opening opening;
    private final int depth;
    private List<Novel> novels;

    public NovelNode(NovelNodeId id, Opening opening, int depth) {
        this.id = id;
        this.opening = opening;
        this.depth = depth;
        init(opening);
    }

    private void init(Novel novel) {
        this.novels = new ArrayList<>();
        novels.add(novel);
    }

    public void publish(Novel novel) {
        validate(novel);

        this.novels.add(novel);
    }

    public int getNovelSize(){
        return this.getNovels().size();
    }

    private void validate(Novel novel) {
        Validation.validate(novels.stream().noneMatch(n->n.getWriterId().getId().equals(novel.getWriterId().getId())),
                new NovelNodeException(String.format("Already exist the writer. WriterId %s, NovelNodeId %s", novel.getWriterId(), id)));
        Validation.validate(novels.stream().noneMatch(n->n.getTitle().equals(novel.getTitle())),
                new NovelNodeException(String.format("Already exist the title. WriterId %s, NovelNodeId %s", novel.getWriterId(), id)));
        Validation.validate(novels.stream().noneMatch(n->n.getId().getId().equals(novel.getId().getId())),
                new NovelNodeException(String.format("Already exist the novel. NovelId %s, NovelNodeId %s", novel.getId(), id)));
    }
}
