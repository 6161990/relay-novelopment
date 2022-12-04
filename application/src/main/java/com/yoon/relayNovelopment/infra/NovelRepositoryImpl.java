package com.yoon.relayNovelopment.infra;

import com.yoon.relayNovelopment.domain.NovelBoard;
import com.yoon.relayNovelopment.domain.NovelBoardId;
import com.yoon.relayNovelopment.domain.NovelRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class NovelRepositoryImpl implements NovelRepository {

    @Override
    public void save(NovelBoard novelBoard) {
    }

    @Override
    public NovelBoard findBy(NovelBoardId novelBoardId) {
        return null;
    }

    @Override
    public List<NovelBoard> findAll() {
        return null;
    }

    @Override
    public void remove(NovelBoardId novelBoardId) {
    }
}
