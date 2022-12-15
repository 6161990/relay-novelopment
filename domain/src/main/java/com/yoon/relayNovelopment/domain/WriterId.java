package com.yoon.relayNovelopment.domain;

import lombok.Value;

import java.util.Objects;

import static org.valid4j.Validation.validate;

@Value(staticConstructor = "of")
public class WriterId {
    String id;

    private WriterId(String id) {
        validate(Objects.nonNull(id), new NovelBoardException("WriterId Value is Null."));
        this.id = id;
    }

    public String getValue() {
        return id;
    }
}
