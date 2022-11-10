package com.yoon.relayNovelopment.domain;

import org.valid4j.Validation;

import java.util.List;

public class Stage {
    private List<Fork> forks;

    public Stage(List<Fork> forks) {
        this.forks = forks;
    }

    public void add(Fork fork){
        forks.add(fork);
    }


}
