package com.yoon.relayNovelopment.domain;

import lombok.Value;

import java.util.Objects;

import static org.valid4j.Validation.validate;

@Value(staticConstructor = "of")
public class NovelBoardId {
    String id;

    private NovelBoardId(String id) {
        validate(Objects.nonNull(id), new NovelBoardException("NovelBoardId Value is Null."));
        this.id = id;
    }

}
