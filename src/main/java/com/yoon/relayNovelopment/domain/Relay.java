package com.yoon.relayNovelopment.domain;

import lombok.Getter;

import java.util.List;

public class Relay {
    @Getter
    private List<Fork> forks;

    public Relay(List<Fork> forks) {
        this.forks = forks;
    }

    public void add(Fork fork){
        forks.add(fork);
    }


}
