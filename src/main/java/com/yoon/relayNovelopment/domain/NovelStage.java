package com.yoon.relayNovelopment.domain;

import lombok.Getter;
import lombok.ToString;
import org.valid4j.Validation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Getter
@ToString
public class NovelStage {
    private final NovelStageId id;
    private final Opening opening;
    private final int maxStageSize;

    @Getter
    private List<Relay> relays;

    public NovelStage(NovelStageId id, Opening opening, int maxStageSize) {
        this.id = id;
        this.opening = opening;
        this.maxStageSize = maxStageSize;
    }

    public void createRelay(Fork fork) {
        if (relays == null){
            init(fork);
        }

        if(relays.stream().anyMatch(e->e.getForks().isEmpty())){
        }

        this.relays.get(0).add(fork);
    }

    private void init(Fork fork) {
        this.relays = new ArrayList<>();
        List<Fork> forks = new ArrayList<>();
        forks.add(fork);
        relays.add(new Relay(forks));
    }

    public int getNovelSize(){
        return this.getRelays() == null ? 0 : this.getRelays().size();
    }

    private void validate(Fork fork) {
        Validation.validate(relays.stream().noneMatch(n-> n.getForks().stream().anyMatch(f->f.getWriterId().getId().equals(fork.getWriterId().getId()))),
                new NovelNodeException(String.format("Already exist the writer. WriterId %s, NovelNodeId %s", fork.getWriterId(), id)));
        Validation.validate(relays.stream().noneMatch(n-> n.getForks().stream().anyMatch(f->f.getTitle().equals(fork.getTitle()))),
                new NovelNodeException(String.format("Already exist the title. WriterId %s, NovelNodeId %s", fork.getWriterId(), id)));
    }

}
