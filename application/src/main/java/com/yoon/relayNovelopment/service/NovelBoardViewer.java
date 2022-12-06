package com.yoon.relayNovelopment.service;

import com.yoon.relayNovelopment.domain.NovelBoard;
import com.yoon.relayNovelopment.domain.NovelBoardId;
import com.yoon.relayNovelopment.domain.NovelRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NovelBoardViewer {

    private final NovelRepository repository;

    public NovelBoard findBy(NovelBoardId id){
        return repository.findBy(id).orElseThrow(()
                -> new NovelBoardNotFoundException(String.format("This NovelBoard Not Found. NovelBoardId is %s.", id.getId())));
    }

    public List<NovelBoard> findAll(){
        return repository.findAll();
    }
}