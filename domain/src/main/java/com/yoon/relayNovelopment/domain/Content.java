package com.yoon.relayNovelopment.domain;

import java.util.Objects;

import static org.valid4j.Validation.*;

public class Content {
    private final String value;

    public static Content of(String value) {
        return new Content(value);
    }

    private Content(String value) {
        this.value = value;
    }

    public String getValue() {
        validate(Objects.nonNull(value), new NovelBoardException("Content is Null."));
        return value;
    }
}
