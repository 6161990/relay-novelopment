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
    boolean isClosed;

    @With
    Novels novels;

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
                .isClosed(novelBoard.isClosed())
                .novels(novelBoard.getNovels())
                .version(novelBoard.getVersion())
                .build();
    }


    public static NovelBoard convert(SpringJdbcNovelBoard entity) {
        return new NovelBoard(
                entity.getNovelBoardId(),
                entity.getOpening(),
                entity.isClosed(),
                entity.getNovels(),
                entity.getCreatedAt(),
                entity.getDeletedAt(),
                entity.getVersion());
    }
}
