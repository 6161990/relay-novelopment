package com.yoon.relayNovelopment.repository;

import com.yoon.relayNovelopment.domain.NovelBoardId;
import com.yoon.relayNovelopment.entity.SpringJdbcNovelBoard;
import org.springframework.data.repository.Repository;

import java.util.List;
import java.util.Optional;

public interface SpringJdbcNovelRepository extends Repository<SpringJdbcNovelBoard, NovelBoardId> {
    void save(SpringJdbcNovelBoard novelBoard);
    Optional<SpringJdbcNovelBoard> findByNovelBoardId(NovelBoardId novelBoardId);
    List<SpringJdbcNovelBoard> findAll();
}


