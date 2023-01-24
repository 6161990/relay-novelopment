package com.yoon.relayNovelopment.domain;

public class ParentNovelKeyFinder {

    public ArticleKey getParentBy(NovelBoard novelBoard) {
        if(novelBoard.getNovelSize() == 0) {
            return novelBoard.getOpening().getOpeningKey();
        } else {
            return novelBoard.getArticles().get(novelBoard.getNovelSize() - 1).getRelayNovelKey();
        }
    }

    public ArticleKey getParentForForkBy(NovelBoard novelBoard) {
        return novelBoard.getArticles().get(novelBoard.getNovelSize() - 1).getParentArticleKey();
    }
}
