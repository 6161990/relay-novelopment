package com.yoon.relayNovelopment.domain;

import com.yoon.relayNovelopment.entity.SpringJdbcNovelBoard;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

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
        Optional<SpringJdbcNovelBoard> optional = repository.findBy(novelBoardId);

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
    public void remove(NovelBoardId novelBoardId) {
        repository.remove(novelBoardId);
    }
}
