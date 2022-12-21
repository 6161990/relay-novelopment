package com.yoon.relayNovelopment.repository;

import com.yoon.relayNovelopment.domain.NovelBoard;
import com.yoon.relayNovelopment.domain.NovelBoardId;
import com.yoon.relayNovelopment.domain.NovelRepository;
import com.yoon.relayNovelopment.entity.SpringJdbcNovelBoard;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class NovelRepositoryAdapter implements NovelRepository {

    private final SpringJdbcNovelRepository repository;

    @Override
    public void save(NovelBoard novelBoard) {
        SpringJdbcNovelBoard springJdbcNovelBoard = SpringJdbcNovelBoard.convert(novelBoard);
        repository.save(springJdbcNovelBoard);
    }

    @Override
    public NovelBoard findBy(NovelBoardId novelBoardId) {
        Optional<SpringJdbcNovelBoard> optional = repository.findByNovelBoardId(novelBoardId);

        if (optional.isEmpty()) {
            return null;
        }

        return SpringJdbcNovelBoard.convert(optional.get());
    }

    @Override
    public List<NovelBoard> findAll() {
        List<SpringJdbcNovelBoard> find = repository.findAll();

        return find.stream().map(SpringJdbcNovelBoard::convert).collect(Collectors.toList());
    }

    @Override
    public void delete(NovelBoardId novelBoardId) {
        NovelBoard novelBoard = findBy(novelBoardId);
        SpringJdbcNovelBoard springJdbcNovelBoard = SpringJdbcNovelBoard.convert(novelBoard);
        springJdbcNovelBoard.withDeletedAt(LocalDateTime.now());
        repository.save(springJdbcNovelBoard);
    }
}
