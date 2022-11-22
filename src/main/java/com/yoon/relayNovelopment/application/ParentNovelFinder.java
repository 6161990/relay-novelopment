package com.yoon.relayNovelopment.application;

import com.yoon.relayNovelopment.domain.NovelBoard;
import com.yoon.relayNovelopment.domain.NovelId;

public class ParentNovelFinder {

    public NovelId getParentBy(NovelBoard novelBoard) {
        if(novelBoard.getNovelSize() == 0) {
            return novelBoard.getOpening().getId();
        } else {
            return novelBoard.getNovels().get(novelBoard.getNovelSize() - 1).getId();
        }
    }

    public NovelId getParentForForkBy(NovelBoard novelBoard) {
        return novelBoard.getNovels().get(novelBoard.getNovelSize() - 1).getParentNovelId();
    }
}
