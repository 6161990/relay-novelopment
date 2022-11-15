package com.yoon.relayNovelopment.domain;

import lombok.Getter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

import static org.valid4j.Validation.validate;

@Getter
@ToString
public class NovelBoard {
    private final NovelStageId id;
    private final Opening opening;
    private final int maxStageSize;

    @Getter
    private List<Stage> stages;

    public NovelBoard(NovelStageId id, Opening opening, int maxStageSize) {
        this.id = id;
        this.opening = opening;
        this.maxStageSize = maxStageSize;
    }

    public void relay(Fork fork) {
        if (stages == null || stages.isEmpty()){
            init();
        }

        valid(fork); // TODO maxStageSize 가 일정 값 이상이면 throw

        this.stages.get(stages.size() - 1).add(fork);
        // TODO 시점마다 FORK 가 적용될 수 있는 Realy 가 있다.
        // TODO 맨 마지막 steps 이 아니다.
    }

    private void init() {
        this.stages = new ArrayList<>();
        List<Fork> forks = new ArrayList<>();
        stages.add(new Stage(forks));
    }

    public long getNovelSize(){
        return this.getStages() == null ? 0 : this.stages.stream().mapToInt(e->e.getForks().size()).sum();
    }

    private void valid(Fork fork) {
        validate(stages.stream().noneMatch(n-> n.getForks().stream().anyMatch(f->f.getWriterId().getId().equals(fork.getWriterId().getId()))),
                new NovelNodeException(String.format("Already exist the writer. WriterId %s, NovelNodeId %s", fork.getWriterId(), id)));
        validate(stages.stream().noneMatch(n-> n.getForks().stream().anyMatch(f->f.getTitle().equals(fork.getTitle()))),
                new NovelNodeException(String.format("Already exist the title. WriterId %s, NovelNodeId %s", fork.getWriterId(), id)));
    }

}
