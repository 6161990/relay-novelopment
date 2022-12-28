package com.yoon.relayNovelopment.repository;

import com.yoon.relayNovelopment.config.ConversionConfig;
import com.yoon.relayNovelopment.domain.*;
import com.yoon.relayNovelopment.entity.SpringJdbcNovelBoard;
import io.zonky.test.db.AutoConfigureEmbeddedDatabase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

import static io.zonky.test.db.AutoConfigureEmbeddedDatabase.DatabaseType.POSTGRES;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Sql(scripts = "classpath:relay-novel.sql")
@Transactional
@SpringJUnitConfig(classes = {ConversionConfig.class, SpringDataJdbcConfig.class})
@AutoConfigureEmbeddedDatabase(type = POSTGRES)
class SpringJdbcNovelBoardRepositoryTest {

    private static final NovelBoardId NOVEL_BOARD_ID = NovelBoardId.of("boardId");

    @Autowired
    SpringJdbcNovelRepository sut;

    @BeforeEach
    void setUp() {
        SpringJdbcNovelBoard springJdbcNovelBoard = SpringJdbcNovelBoard.builder()
                .novelBoardId(NOVEL_BOARD_ID)
                .opening(Opening.of(OpeningKey.of("key"), WriterId.of("writer"), Title.of("title"), Content.of("content")))
                .novels(getNovels())
                .isClosed(false)
                .build();

        sut.save(springJdbcNovelBoard);
    }

    @Test
    void findOne() {
        Optional<SpringJdbcNovelBoard> actual = sut.findByNovelBoardId(NOVEL_BOARD_ID);

        assertTrue(actual.isPresent());
    }

    @Test
    void when_not_exist_findOne() {
        Optional<SpringJdbcNovelBoard> actual = sut.findByNovelBoardId(NovelBoardId.of("NOT_EXIST"));

        assertThat(actual.isPresent()).isFalse();
    }
    @Test
    void findAll() {
        List<SpringJdbcNovelBoard> findAll = sut.findAll();

        assertThat(findAll.size()).isEqualTo(1);
    }

    private Novels getNovels() {
        List<Novel> novels = new ArrayList<>();
        novels.add(Novel.of(RelayNovelKey.of("novelKey1"), ParentNovelKey.of("parentKey1"), WriterId.of("writer1"), Title.of("title1"), Content.of("content1")));
        novels.add(Novel.of(RelayNovelKey.of("novelKey2"), ParentNovelKey.of("parentKey2"), WriterId.of("writer2"), Title.of("title2"), Content.of("content2")));

        return new Novels(novels);
    }
}