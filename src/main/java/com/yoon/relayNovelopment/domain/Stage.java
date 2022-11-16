package com.yoon.relayNovelopment.domain;

import lombok.Getter;

import java.util.List;

@Getter
public class Stage {

    private RelayId id;
    private List<Novel> novels;
    private StageState stageState;
    private boolean isClosed;

    public Stage(List<Novel> novels) {
        this.novels = novels;
    }

    public void add(Novel novel){
        novels.add(novel);
    }

    public void setClosed(){
        this.isClosed = true;
    }


}
