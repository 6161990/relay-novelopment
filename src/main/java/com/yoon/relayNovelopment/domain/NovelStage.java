package com.yoon.relayNovelopment.domain;

import lombok.Getter;
import lombok.ToString;
import org.valid4j.Validation;

import java.util.ArrayList;
import java.util.List;

@Getter
@ToString
public class NovelStage {
    private final NovelStageId id;
    private final Opening opening;
    private final int maxStageSize;

    @Getter
    private List<Step> steps;

    public NovelStage(NovelStageId id, Opening opening, int maxStageSize) {
        this.id = id;
        this.opening = opening;
        this.maxStageSize = maxStageSize;
    }

    public void relay(Fork fork) {
        if (steps == null || steps.isEmpty()){
            init();
        }

        this.steps.get(steps.size() - 1).add(fork); // TODO 시점마다 FORK 가 적용될 수 있는 Realy 가 있다.
    }

    private void init() {
        this.steps = new ArrayList<>();
        List<Fork> forks = new ArrayList<>();
        steps.add(new Step(forks));
    }

    public long getNovelSize(){
        return this.getSteps() == null ? 0 : this.steps.stream().mapToInt(e->e.getForks().size()).sum();
    }

    private void validate(Fork fork) {
        Validation.validate(steps.stream().noneMatch(n-> n.getForks().stream().anyMatch(f->f.getWriterId().getId().equals(fork.getWriterId().getId()))),
                new NovelNodeException(String.format("Already exist the writer. WriterId %s, NovelNodeId %s", fork.getWriterId(), id)));
        Validation.validate(steps.stream().noneMatch(n-> n.getForks().stream().anyMatch(f->f.getTitle().equals(fork.getTitle()))),
                new NovelNodeException(String.format("Already exist the title. WriterId %s, NovelNodeId %s", fork.getWriterId(), id)));
    }

}
