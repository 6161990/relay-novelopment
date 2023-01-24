package com.yoon.relayNovelopment.application;

import com.yoon.relayNovelopment.domain.*;
import com.yoon.relayNovelopment.service.NovelCommand;
import com.yoon.relayNovelopment.service.NovelCreateCommand;
import com.yoon.relayNovelopment.service.NovelEditCommand;
import org.springframework.stereotype.Component;

@Component
public class CommandFactory {

    public NovelCommand createBy(NovelCreateRequest request) {
        return new NovelCreateCommand(WriterId.of(request.getWriterId()), Title.of(request.getTitle()), Content.of(request.getContent()), Genre.valueOf(request.getGenre()));
    }

    public NovelCommand createBy(NovelBoardId id, NovelCreateRequest request) {
        return new NovelEditCommand(id, WriterId.of(request.getWriterId()), Title.of(request.getTitle()), Content.of(request.getContent()), Genre.valueOf(request.getGenre()));
    }
}
