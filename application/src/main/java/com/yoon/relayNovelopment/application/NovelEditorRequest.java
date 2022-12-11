package com.yoon.relayNovelopment.application;

import com.yoon.relayNovelopment.domain.NovelBoardId;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class NovelEditorRequest {

    @Valid
    String novelBoardId;

    @NotEmpty
    String writerId;

    @NotNull
    String title;

    @NotNull
    String content;
}