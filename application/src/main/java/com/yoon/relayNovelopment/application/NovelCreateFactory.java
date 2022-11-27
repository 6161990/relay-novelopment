package com.yoon.relayNovelopment.application;

import com.yoon.relayNovelopment.domain.Novel;
import com.yoon.relayNovelopment.domain.NovelBoard;
import com.yoon.relayNovelopment.domain.NovelId;
import com.yoon.relayNovelopment.domain.RelayNovelId;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NovelCreateFactory {

    private final IdGeneratore idGeneratore;

    public Novel create(NovelBoard novelBoard, NovelCreateCommand command) {
        NovelId parentId = ParentNovelFinder.getParentBy(novelBoard);

        return Novel.of(RelayNovelId.of("boardId"), parentId,
                        command.getWriterId(), command.getTitle(), command.getContent());
    }

    public Novel createForFork(NovelBoard novelBoard, NovelCreateCommand command) {
        NovelId parentId = ParentNovelFinder.getParentForForkBy(novelBoard);

        return Novel.of(RelayNovelId.of("boardId"), parentId, // FIXME idgen 을 통한 RelayNovelId
                command.getWriterId(), command.getTitle(), command.getContent());
    }
}
