package com.yoon.relayNovelopment.service;

import com.yoon.relayNovelopment.domain.Content;
import com.yoon.relayNovelopment.domain.NovelBoardId;
import com.yoon.relayNovelopment.domain.Title;
import com.yoon.relayNovelopment.domain.WriterId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.Valid;

@NoArgsConstructor
@AllArgsConstructor
public class NovelCreateCommand implements NovelCommand {

    @Valid
    String writerId;

    @Valid
    String title;

    @Valid
    String content;

    public WriterId getWriterId() {
        return WriterId.of(writerId);
    }

    public Title getTitle() {
        return Title.of(title);
    }

    public Content getContent() {
        return Content.of(content);
    }
}
