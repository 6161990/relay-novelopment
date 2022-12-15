package com.yoon.relayNovelopment.domain;

import lombok.Value;

@Value(staticConstructor = "of")
public class NovelBoardId {
    String id;

    private NovelBoardId(String id) {
        this.id = id;
    }

}
