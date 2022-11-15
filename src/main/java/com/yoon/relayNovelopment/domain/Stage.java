package com.yoon.relayNovelopment.domain;

import lombok.Getter;

import java.util.List;

@Getter
public class Stage {

    private RelayId id;
    private List<Fork> forks;
    private StageState stageState;
    private boolean isClosed;

    public Stage(List<Fork> forks) {
        this.forks = forks;
    }

    public void add(Fork fork){
        forks.add(fork);
    }

    public void setClosed(){
        this.isClosed = true;
    }


}
