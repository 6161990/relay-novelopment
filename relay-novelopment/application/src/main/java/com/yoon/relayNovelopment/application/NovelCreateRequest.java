package com.yoon.relayNovelopment.application;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class NovelCreateRequest {

    @NotEmpty
    String writerId;

    @NotNull
    String title;

    @NotNull
    String content;
}
