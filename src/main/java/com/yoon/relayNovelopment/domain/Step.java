package com.yoon.relayNovelopment.domain;

import lombok.Getter;

import java.util.List;

@Getter
public class Step {

    private RelayId id;
    private List<Fork> forks;
    private boolean isClosed;

    public Step(List<Fork> forks) {
        this.forks = forks;
    }

    public void add(Fork fork){
        forks.add(fork);
    }

    public void setClosed(){
        this.isClosed = true;
    }


}
