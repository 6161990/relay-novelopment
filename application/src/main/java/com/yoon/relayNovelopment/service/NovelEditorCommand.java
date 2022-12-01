package com.yoon.relayNovelopment.service;

import com.yoon.relayNovelopment.domain.Content;
import com.yoon.relayNovelopment.domain.NovelBoardId;
import com.yoon.relayNovelopment.domain.Title;
import com.yoon.relayNovelopment.domain.WriterId;
import lombok.Getter;

@Getter
public class NovelEditorCommand implements NovelCommand {

    private final NovelBoardId novelBoardId;
    private final WriterId writerId;
    private final Title title;
    private final Content content;

    public NovelEditorCommand(NovelBoardId novelBoardId, WriterId writerId, Title title, Content content) {
        this.novelBoardId = novelBoardId;
        this.writerId = writerId;
        this.title = title;
        this.content = content;
    }

}
