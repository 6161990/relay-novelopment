package com.yoon.relayNovelopment.entity;

import com.yoon.relayNovelopment.domain.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(value = "novelBoard")
public class SpringJdbcNovelBoard {
    @Id
    @With
    NovelBoardId id;

    @With
    Opening opening;

    @With
    boolean isClosed;

    @With
    Novels novels;

    @CreatedDate
    LocalDateTime createdAt;
    @With
    @Version
    Long version;

    public static SpringJdbcNovelBoard convert(NovelBoard novelBoard) {

        SpringJdbcNovelBoard entity = SpringJdbcNovelBoard.builder()
                .id(novelBoard.getNovelBoardId())
                .opening(novelBoard.getOpening())
                .isClosed(novelBoard.isClosed())
                .novels(novelBoard.getNovels())
                .version(novelBoard.getVersion())
                .build();

        if (Objects.isNull(novelBoard.getNovelBoardId())) {
            return entity;
        }
        return entity.withId(novelBoard.getNovelBoardId());
    }


    public static NovelBoard convert(SpringJdbcNovelBoard entity) {
        return new NovelBoard(
                entity.getId(),
                entity.getOpening(),
                entity.isClosed(),
                entity.getNovels(),
                entity.getCreatedAt(),
                entity.getVersion());
    }
}
