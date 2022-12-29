package com.yoon.relayNovelopment.service;

import com.yoon.relayNovelopment.config.IdGenerator;
import com.yoon.relayNovelopment.domain.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NovelCreateFactory {

    private final IdGenerator idGenerator;
    private final ParentNovelKeyFinder parentNovelKeyFinder;

    public Novel createForRelay(NovelBoard novelBoard, NovelCommand command) {
        NovelKey parentKey = parentNovelKeyFinder.getParentBy(novelBoard);

        return Novel.of(RelayNovelKey.of(idGenerator.getKey(novelBoard.getNovelBoardId(), command.getTitle())), parentKey,
                        command.getWriterId(), command.getTitle(), command.getContent());
    }

    public Novel createForFork(NovelBoard novelBoard, NovelCommand command) {
        NovelKey parentKey = parentNovelKeyFinder.getParentForForkBy(novelBoard);

        return Novel.of(RelayNovelKey.of(idGenerator.getKey(novelBoard.getNovelBoardId(), command.getTitle())), parentKey,
                command.getWriterId(), command.getTitle(), command.getContent());
    }
}
