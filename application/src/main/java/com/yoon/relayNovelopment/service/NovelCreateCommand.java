package com.yoon.relayNovelopment.service;

import com.yoon.relayNovelopment.domain.Content;
import com.yoon.relayNovelopment.domain.Title;
import com.yoon.relayNovelopment.domain.WriterId;
import lombok.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@NoArgsConstructor
@AllArgsConstructor
public class NovelCreateCommand implements NovelCommand {

    @NotEmpty
    String writerId;

    @NotNull
    String title;

    @NotNull
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
