package com.yoon.relayNovelopment.domain;

import java.util.List;

public interface NovelBoardRepository {
    void save(NovelBoard novelBoard);
    NovelBoard findBy(NovelBoardId novelBoardId);
    List<NovelBoard> findAll();

    void delete(NovelBoardId id);
}
