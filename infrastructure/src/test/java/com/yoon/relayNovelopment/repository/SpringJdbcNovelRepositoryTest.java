package com.yoon.relayNovelopment.repository;

import com.yoon.relayNovelopment.config.ConversionConfig;
import com.yoon.relayNovelopment.domain.*;
import com.yoon.relayNovelopment.entity.SpringJdbcNovelBoard;
import io.zonky.test.db.AutoConfigureEmbeddedDatabase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static io.zonky.test.db.AutoConfigureEmbeddedDatabase.DatabaseType.POSTGRES;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.annotation.DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD;

@Sql(scripts = "classpath:relay-novel.sql")
@DirtiesContext(classMode = BEFORE_EACH_TEST_METHOD)
@Transactional
@SpringJUnitConfig(classes = {ConversionConfig.class, SpringDataJdbcConfig.class})
@AutoConfigureEmbeddedDatabase(type = POSTGRES)
class SpringJdbcNovelRepositoryTest {

    private final NovelBoardId NOVEL_BOARD_ID = NovelBoardId.of("boardId");

    @Autowired
    SpringJdbcNovelRepository sut;

    @BeforeEach
    void setUp() {
        SpringJdbcNovelBoard springJdbcNovelBoard = SpringJdbcNovelBoard.builder()
                .id(NOVEL_BOARD_ID)
                .opening(Opening.of(OpeningKey.of("key"), WriterId.of("writer"), Title.of("title"), Content.of("content")))
                .novels(getNovels())
                .isClosed(false)
                .build();

        sut.save(springJdbcNovelBoard);
    }

    @Test
    void findOne() {
        Optional<SpringJdbcNovelBoard> find = sut.findBy(NOVEL_BOARD_ID);

        assertTrue(find.isPresent());
    }


    private Novels getNovels() {
        List<Novel> novels = new ArrayList<>();
        novels.add(Novel.of(RelayNovelKey.of("novelKey1"), ParentNovelKey.of("parentKey1"), WriterId.of("writer1"), Title.of("title1"), Content.of("content1")));
        novels.add(Novel.of(RelayNovelKey.of("novelKey2"), ParentNovelKey.of("parentKey2"), WriterId.of("writer2"), Title.of("title2"), Content.of("content2")));

        return new Novels(novels);
    }
}