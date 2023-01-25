package com.yoon.relayNovelopment.application;

import com.yoon.relayNovelopment.domain.*;
import com.yoon.relayNovelopment.service.ArticleCommand;
import com.yoon.relayNovelopment.service.NovelBoardCreateCommand;
import com.yoon.relayNovelopment.service.NovelBoardEditCommand;
import org.springframework.stereotype.Component;

@Component
public class CommandFactory {

    public ArticleCommand createBy(NovelCreateRequest request) {
        return new NovelBoardCreateCommand(WriterId.of(request.getWriterId()), Title.of(request.getTitle()), Content.of(request.getContent()), Genre.valueOf(request.getGenre()));
    }

    public ArticleCommand createBy(NovelBoardId id, NovelCreateRequest request) {
        return new NovelBoardEditCommand(id, WriterId.of(request.getWriterId()), Title.of(request.getTitle()), Content.of(request.getContent()), Genre.valueOf(request.getGenre()));
    }
}
