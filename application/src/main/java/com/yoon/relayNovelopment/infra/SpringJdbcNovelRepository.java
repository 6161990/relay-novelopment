package com.yoon.relayNovelopment.infra;

import com.yoon.relayNovelopment.domain.NovelBoard;
import com.yoon.relayNovelopment.domain.NovelBoardId;
import com.yoon.relayNovelopment.domain.NovelRepository;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringJdbcNovelRepository implements NovelRepository {
    @Override
    public void save(NovelBoard novelBoard) {

    }

    @Override
    public NovelBoard findBy(NovelBoardId novelBoardId) {
        return null;
    }
}