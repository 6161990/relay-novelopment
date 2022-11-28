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

    private final IdGenerator idGenerator;

    public Novel create(NovelBoard novelBoard, NovelCreateCommand command) {
        NovelId parentId = ParentNovelFinder.getParentBy(novelBoard);

        return Novel.of(RelayNovelId.of(idGenerator.getId()), parentId,
                        command.getWriterId(), command.getTitle(), command.getContent());
    }

    public Novel createForFork(NovelBoard novelBoard, NovelCreateCommand command) {
        NovelId parentId = ParentNovelFinder.getParentForForkBy(novelBoard);

        return Novel.of(RelayNovelId.of(idGenerator.getId()), parentId, // FIXME idgen 을 통한 RelayNovelId id 말고 key 로 !
                command.getWriterId(), command.getTitle(), command.getContent());
    }
}
