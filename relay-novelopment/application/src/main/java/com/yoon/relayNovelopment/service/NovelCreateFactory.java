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

    public Article createForRelay(NovelBoard novelBoard, NovelCommand command) {
        ArticleKey parentKey = parentNovelKeyFinder.getParentBy(novelBoard);

        return Article.of(RelayArticleKey.of(idGenerator.getKey(novelBoard.getNovelBoardId(), command.getTitle())), parentKey,
                        command.getWriterId(), command.getTitle(), command.getContent(), command.getGenre());
    }

    public Article createForFork(NovelBoard novelBoard, NovelCommand command) {
        ArticleKey parentKey = parentNovelKeyFinder.getParentForForkBy(novelBoard);

        return Article.of(RelayArticleKey.of(idGenerator.getKey(novelBoard.getNovelBoardId(), command.getTitle())), parentKey,
                command.getWriterId(), command.getTitle(), command.getContent(),  command.getGenre());
    }
}
