package com.yoon.relayNovelopment.application;

import com.yoon.relayNovelopment.domain.Novel;
import com.yoon.relayNovelopment.domain.NovelBoard;
import com.yoon.relayNovelopment.domain.NovelId;
import com.yoon.relayNovelopment.domain.RelayNovelId;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class NovelCreateFactory {

    private final IdGenerator idGenerator;
    private final ParentNovelFinder parentNovelFinder;

    public Novel create(NovelBoard novelBoard, NovelEditCommand command) {
        NovelId parentId = parentNovelFinder.getParentBy(novelBoard);

        return Novel.of(RelayNovelId.of("boardId"), parentId,
                        command.getWriterId(), command.getTitle(), command.getContent(), command.getProps());
    }

    public Novel createForFork(NovelBoard novelBoard, NovelEditCommand command) {
        NovelId parentId = parentNovelFinder.getParentForForkBy(novelBoard);

        return Novel.of(RelayNovelId.of("boardId"), parentId, // FIXME idgen 을 통한 RelayNovelId
                command.getWriterId(), command.getTitle(), command.getContent(), command.getProps());
    }
}
