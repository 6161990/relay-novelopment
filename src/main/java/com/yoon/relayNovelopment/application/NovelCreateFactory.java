package com.yoon.relayNovelopment.application;

import com.yoon.relayNovelopment.domain.Novel;
import com.yoon.relayNovelopment.domain.NovelBoard;
import com.yoon.relayNovelopment.domain.NovelId;
import com.yoon.relayNovelopment.domain.RelayNovelId;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class NovelCreateFactory {

    private final IdGenerator idGenerator;
    private final ParentFinder parentFinder;

    public Novel create(NovelBoard novelBoard, NovelEditCommand command) {
        NovelId parentId = parentFinder.getParentBy(novelBoard);

        return Novel.of(RelayNovelId.of(idGenerator.gen()), parentId,
                        command.getWriterId(), command.getTitle(), command.getContent(), command.getProps());
    }
}
