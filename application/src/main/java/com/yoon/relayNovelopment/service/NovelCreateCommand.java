package com.yoon.relayNovelopment.service;

import com.yoon.relayNovelopment.domain.Content;
import com.yoon.relayNovelopment.domain.Title;
import com.yoon.relayNovelopment.domain.WriterId;
import lombok.Getter;

@Getter
public class NovelCreateCommand implements NovelCommand {

    private final WriterId writerId;
    private final Title title;
    private final Content content;

    public NovelCreateCommand(WriterId writerId, Title title, Content content) {
        this.writerId = writerId;
        this.title = title;
        this.content = content;
    }
}
