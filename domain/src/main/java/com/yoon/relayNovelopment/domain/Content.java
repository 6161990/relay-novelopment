package com.yoon.relayNovelopment.domain;

import lombok.Value;

import java.util.Objects;

import static org.valid4j.Validation.*;

@Value(staticConstructor = "of")
public class Content {
    String value;

    private Content(String value) {
        validate(Objects.nonNull(value), new NovelBoardException("Content Value is Null."));
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
