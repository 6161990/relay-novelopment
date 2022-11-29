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
        NovelId parentId = ParentNovelFinder.getParentBy(novelBoard);

        return Novel.of(RelayNovelId.of(idGenerator.getId()), parentId,
                        command.getWriterId(), command.getTitle(), command.getContent());
    }

    public Novel createForFork(NovelBoard novelBoard, NovelCommand command) {
        NovelId parentId = ParentNovelFinder.getParentForForkBy(novelBoard);

        return Novel.of(RelayNovelId.of(idGenerator.getId()), parentId, // FIXME idgen 을 통한 RelayNovelId id 말고 key 로 ! 왜냐면 idGen 으로 만들기에는 과하다..
                command.getWriterId(), command.getTitle(), command.getContent());
    }
}
