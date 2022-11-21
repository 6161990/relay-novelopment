package com.yoon.relayNovelopment.application;

import com.yoon.relayNovelopment.domain.Novel;
import com.yoon.relayNovelopment.domain.NovelId;
import com.yoon.relayNovelopment.domain.RelayNovelId;

public class NovelGenerator {

    private final IdGenerator idGenerator;

    public NovelGenerator(IdGenerator idGenerator) {
        this.idGenerator = idGenerator;
    }

    public Novel gen(NovelId parentId, NovelEditCommand command) {
        return Novel.of(RelayNovelId.of("boardId"), parentId,
                        command.getWriterId(), command.getTitle(), command.getContent(), command.getProps());
    }
}
