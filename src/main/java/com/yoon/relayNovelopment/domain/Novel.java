package com.yoon.relayNovelopment.domain;

import lombok.Getter;
import lombok.ToString;
import org.valid4j.Validation;

import java.util.ArrayList;
import java.util.List;

@Getter
@ToString
public class Novel {
    private final NovelNodeId id;
    private final Opening opening;
    private final int maxStage;
    private List<Stage> stages;

    public Novel(NovelNodeId id, Opening opening, int maxStage) {
        this.id = id;
        this.opening = opening;
        this.maxStage = maxStage;
        init();
    }

    private void init() {
        this.stages = new ArrayList<>();
    }

    public void publish(Stage stage) {
        validate(stage);

        this.stages.add(stage);
    }

    public int getNovelSize(){
        return this.getStages().size();
    }

    private void validate(Fork fork) {
        Validation.validate(stages.stream().noneMatch(n->n.getWriterId().getId().equals(fork.getWriterId().getId())),
                new NovelNodeException(String.format("Already exist the writer. WriterId %s, NovelNodeId %s", fork.getWriterId(), id)));
        Validation.validate(stages.stream().noneMatch(n->n.getTitle().equals(fork.getTitle())),
                new NovelNodeException(String.format("Already exist the title. WriterId %s, NovelNodeId %s", fork.getWriterId(), id)));
        Validation.validate(stages.stream().noneMatch(n->n.getId().getId().equals(fork.getId().getId())),
                new NovelNodeException(String.format("Already exist the fork. NovelId %s, NovelNodeId %s", fork.getId(), id)));
    }
}
