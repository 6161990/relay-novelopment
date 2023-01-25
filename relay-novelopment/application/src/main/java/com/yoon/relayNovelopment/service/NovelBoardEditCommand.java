package com.yoon.relayNovelopment.service;

import com.yoon.relayNovelopment.domain.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import javax.validation.Valid;

@Builder
@Data
@AllArgsConstructor
public class NovelBoardEditCommand implements ArticleCommand {

    @Valid
    NovelBoardId novelBoardId;

    @Valid
    WriterId writerId;

    @Valid
    Title title;

    @Valid
    Content content;

    @Valid
    Genre genre;
}
