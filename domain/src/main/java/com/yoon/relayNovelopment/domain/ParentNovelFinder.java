package com.yoon.relayNovelopment.domain;

public class ParentNovelFinder {

    public static NovelId getParentBy(NovelBoard novelBoard) {
        if(novelBoard.getNovelSize() == 0) {
            return novelBoard.getOpening().getId();
        } else {
            return novelBoard.getNovels().get(novelBoard.getNovelSize() - 1).getId();
        }
    }

    public static NovelId getParentForForkBy(NovelBoard novelBoard) {
        return novelBoard.getNovels().get(novelBoard.getNovelSize() - 1).getParentNovelId();
    }
}
