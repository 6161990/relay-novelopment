package com.yoon.relayNovelopment.application;

import com.yoon.relayNovelopment.domain.Content;
import com.yoon.relayNovelopment.domain.NovelBoardId;
import com.yoon.relayNovelopment.domain.Title;
import com.yoon.relayNovelopment.domain.WriterId;
import com.yoon.relayNovelopment.service.NovelCommand;
import com.yoon.relayNovelopment.service.NovelCreateCommand;
import com.yoon.relayNovelopment.service.NovelEditCommand;
import org.springframework.stereotype.Component;

@Component
public class CommandFactory {

    public NovelCommand createBy(NovelCreateRequest request) {
        return new NovelCreateCommand(WriterId.of(request.getWriterId()), Title.of(request.getTitle()), Content.of(request.getContent()));
    }

    public NovelCommand createBy(NovelBoardId id, NovelCreateRequest request) {
        return new NovelEditCommand(id, WriterId.of(request.getWriterId()), Title.of(request.getTitle()), Content.of(request.getContent()));
    }
}
