package com.yoon.relayNovelopment.domain;

import com.yoon.relayNovelopment.entity.SpringJdbcNovelBoard;
import org.springframework.data.repository.Repository;

import java.util.List;
import java.util.Optional;

public interface SpringJdbcNovelRepository extends Repository<SpringJdbcNovelBoard,NovelBoardId> {
    void save(SpringJdbcNovelBoard novelBoard);
    Optional<SpringJdbcNovelBoard> findBy(NovelBoardId novelBoardId);
    List<SpringJdbcNovelBoard> findAll();
    void remove(NovelBoardId novelBoardId);
}


