package com.yoon.relayNovelopment.domain;

public class ParentNovelFinder {

    public static NovelKey getParentBy(NovelBoard novelBoard) {
        if(novelBoard.getNovelSize() == 0) {
            return novelBoard.getOpening().getKey();
        } else {
            return novelBoard.getNovels().get(novelBoard.getNovelSize() - 1).getId();
        }
    }

    public static NovelKey getParentForForkBy(NovelBoard novelBoard) {
        return novelBoard.getNovels().get(novelBoard.getNovelSize() - 1).getParentNovelKey();
    }
}
