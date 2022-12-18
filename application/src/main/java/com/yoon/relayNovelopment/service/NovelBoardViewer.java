package com.yoon.relayNovelopment.service;

import com.yoon.relayNovelopment.domain.NovelBoard;
import com.yoon.relayNovelopment.domain.NovelBoardException;
import com.yoon.relayNovelopment.domain.NovelBoardId;
import com.yoon.relayNovelopment.domain.NovelRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class NovelBoardViewer {

    private final NovelRepository repository;

    public NovelBoard findBy(NovelBoardId id){
        NovelBoard novelBoard = repository.findBy(id);

        if (Objects.isNull(novelBoard)){
            throw new NovelBoardException(String.format("This NovelBoard Not Found. NovelBoardId is %s.", id.getId()));
        }

        return novelBoard;
    }

    public List<NovelBoard> findAll(){
        return repository.findAll();
    }
}