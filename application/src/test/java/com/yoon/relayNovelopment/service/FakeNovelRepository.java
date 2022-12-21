package com.yoon.relayNovelopment.service;

import com.yoon.relayNovelopment.domain.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.valid4j.Validation.validate;

public class FakeNovelRepository implements NovelRepository {

    List<NovelBoard> novelBoards = new ArrayList<>();

    @Override
    public void save(NovelBoard novelBoard) {
        novelBoards.add(novelBoard);
    }

    @Override
    public NovelBoard findBy(NovelBoardId novelBoardId) {
        if(novelBoards.isEmpty()) {
            novelBoards.add(new NovelBoard(novelBoardId, Opening.of(OpeningKey.of("openId"), WriterId.of("writer"), Title.of("title"), Content.of("value"))));
        }
        return novelBoards.stream().filter(i->i.getNovelBoardId().equals(novelBoardId)).findFirst().get();
    }

    @Override
    public List<NovelBoard> findAll() {
        return novelBoards;
    }

    @Override
    public void delete(NovelBoardId novelBoardId) {
        validate(!novelBoards.isEmpty(), new NovelBoardException("NovelBoard is Null"));

        novelBoards.removeIf(i->i.getNovelBoardId().equals(novelBoardId));
    }
}
