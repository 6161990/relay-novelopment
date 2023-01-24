package com.yoon.relayNovelopment.entity;

import com.yoon.relayNovelopment.domain.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(value = "relay_novel")
public class SpringJdbcNovelBoard {
    @Id
    @With
    NovelBoardId novelBoardId;

    @With
    Opening opening;

    @With
    Genre genre;

    @With
    boolean isClosed;

    @With
    Articles articles;

    @CreatedDate
    LocalDateTime createdAt;

    @With
    LocalDateTime deletedAt;
    @With
    @Version
    Long version;

    public static SpringJdbcNovelBoard convert(NovelBoard novelBoard) {
        return SpringJdbcNovelBoard.builder()
                .novelBoardId(novelBoard.getNovelBoardId())
                .opening(novelBoard.getOpening())
                .genre(novelBoard.getGenre())
                .isClosed(novelBoard.isClosed())
                .articles(novelBoard.getArticles())
                .version(novelBoard.getVersion())
                .build();
    }


    public static NovelBoard convert(SpringJdbcNovelBoard entity) {
        return new NovelBoard(
                entity.getNovelBoardId(),
                entity.getOpening(),
                entity.getGenre(),
                entity.isClosed(),
                entity.getArticles(),
                entity.getCreatedAt(),
                entity.getDeletedAt(),
                entity.getVersion());
    }
}
