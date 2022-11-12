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
    private List<Relay> relays;

    public NovelStage(NovelStageId id, Opening opening, int maxStageSize) {
        this.id = id;
        this.opening = opening;
        this.maxStageSize = maxStageSize;
    }

    public void createRelay(Relay relay) {
        if (relays == null){
            init();
        }


        this.relays.add(relay);
    }

    private void init() {
        this.relays = new ArrayList<>();
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
