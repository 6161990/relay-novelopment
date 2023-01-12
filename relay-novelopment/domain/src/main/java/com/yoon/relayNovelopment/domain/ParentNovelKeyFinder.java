package com.yoon.relayNovelopment.domain;

public class ParentNovelKeyFinder {

    public NovelKey getParentBy(NovelBoard novelBoard) {
        if(novelBoard.getNovelSize() == 0) {
            return novelBoard.getOpening().getOpeningKey();
        } else {
            return novelBoard.getNovels().get(novelBoard.getNovelSize() - 1).getRelayNovelKey();
        }
    }

    public NovelKey getParentForForkBy(NovelBoard novelBoard) {
        return novelBoard.getNovels().get(novelBoard.getNovelSize() - 1).getParentNovelKey();
    }
}
