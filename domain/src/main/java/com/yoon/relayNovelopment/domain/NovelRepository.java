package com.yoon.relayNovelopment.domain;

import java.util.List;
import java.util.Optional;

public interface NovelRepository {
    void save(NovelBoard novelBoard);
    Optional<NovelBoard> findBy(NovelBoardId novelBoardId);
    List<NovelBoard> findAll();
    void remove(NovelBoardId novelBoardId);
}
