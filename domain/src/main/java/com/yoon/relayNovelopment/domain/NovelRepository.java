package com.yoon.relayNovelopment.domain;

import java.util.List;

public interface NovelRepository {
    void save(NovelBoard novelBoard);
    NovelBoard findBy(NovelBoardId novelBoardId);
    List<NovelBoard> findAll();
    void remove(NovelBoardId novelBoardId);
}
