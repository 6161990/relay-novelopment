package com.yoon.relayNovelopment.domain;

import lombok.Value;

import java.util.Objects;

import static org.valid4j.Validation.validate;

@Value(staticConstructor = "of")
public class Title {
    String name;

    private Title(String name) {
        validate(Objects.nonNull(name), new NovelBoardException("Title Value is Null."));
        this.name = name;
    }

}
