package com.yoon.relayNovelopment.application;

import com.yoon.relayNovelopment.domain.*;

import java.util.ArrayList;
import java.util.List;

public class FakeNovelRepository implements NovelRepository {

    List<NovelBoard> novelBoards = new ArrayList<>();

    @Override
    public void save(NovelBoard novelBoard) {
        novelBoards.add(novelBoard);
    }

    @Override
    public NovelBoard findBy(NovelBoardId novelBoardId) {
        if(novelBoards.isEmpty()) {
            novelBoards.add(new NovelBoard(novelBoardId, Opening.of(OpeningId.of("openId"), WriterId.of("writer"), Title.of("title"), Content.of("value"))));
        }
        return novelBoards.stream().filter(i->i.getId().equals(novelBoardId)).findFirst().get();
    }
}
