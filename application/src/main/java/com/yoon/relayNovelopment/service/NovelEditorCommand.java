package com.yoon.relayNovelopment.service;

import com.yoon.relayNovelopment.domain.Content;
import com.yoon.relayNovelopment.domain.NovelBoardId;
import com.yoon.relayNovelopment.domain.Title;
import com.yoon.relayNovelopment.domain.WriterId;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import javax.validation.Valid;

@Builder
@Data
@AllArgsConstructor
public class NovelEditorCommand implements NovelCommand {

    @Valid
    NovelBoardId novelBoardId;

    @Valid
    WriterId writerId;

    @Valid
    Title title;

    @Valid
    Content content;

}
