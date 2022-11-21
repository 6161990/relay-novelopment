package com.yoon.relayNovelopment.domain;

public interface NovelRepository {
    void save(NovelBoard novelBoard);
    NovelBoard findBy(NovelBoardId novelBoardId);

}
