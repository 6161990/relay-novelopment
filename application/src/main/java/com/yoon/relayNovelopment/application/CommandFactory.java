package com.yoon.relayNovelopment.application;

import com.yoon.relayNovelopment.domain.Content;
import com.yoon.relayNovelopment.domain.NovelBoardId;
import com.yoon.relayNovelopment.domain.Title;
import com.yoon.relayNovelopment.domain.WriterId;
import com.yoon.relayNovelopment.service.NovelCreateCommand;
import com.yoon.relayNovelopment.service.NovelEditorCommand;
import org.springframework.stereotype.Component;

@Component
public class CommandFactory {

    public NovelCreateCommand create(NovelCreateRequest request) {
        return new NovelCreateCommand(WriterId.of(request.getWriterId()), Title.of(request.getTitle()), Content.of(request.getContent()));
    }

    public NovelEditorCommand editor(NovelEditorRequest novelEditorRequest) {
        return new NovelEditorCommand(NovelBoardId.of(novelEditorRequest.getNovelBoardId()),WriterId.of(novelEditorRequest.getWriterId()), Title.of(novelEditorRequest.getTitle()), Content.of(novelEditorRequest.getContent()));
    }
}
