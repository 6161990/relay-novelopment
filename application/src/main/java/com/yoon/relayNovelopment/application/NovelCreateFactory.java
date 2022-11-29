package com.yoon.relayNovelopment.application;

import com.yoon.relayNovelopment.config.IdGenerator;
import com.yoon.relayNovelopment.domain.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NovelCreateFactory {

    private final IdGenerator idGenerator;

    public Novel create(NovelBoard novelBoard, NovelCommand command) {
        NovelKey parentId = ParentNovelFinder.getParentBy(novelBoard);

        return Novel.of(RelayNovelKey.of(idGenerator.getKey(novelBoard.getId(), command.getTitle())), parentId,
                        command.getWriterId(), command.getTitle(), command.getContent());
    }

    public Novel createForFork(NovelBoard novelBoard, NovelCommand command) {
        NovelKey parentId = ParentNovelFinder.getParentForForkBy(novelBoard);

        return Novel.of(RelayNovelKey.of(idGenerator.getKey(novelBoard.getId(), command.getTitle())), parentId,
                command.getWriterId(), command.getTitle(), command.getContent());
    }
}
