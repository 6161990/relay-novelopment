package com.yoon.relayNovelopment.domain;

public enum StageState {
    BEGINNING(2), RISING_ACTION(4), CRISIS(8), CLIMAX(8), ENDING(8);

    StageState(int maxFork) {

    }
}
