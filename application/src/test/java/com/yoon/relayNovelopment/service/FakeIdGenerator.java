package com.yoon.relayNovelopment.service;

import com.yoon.relayNovelopment.config.IdGenerator;
import com.yoon.relayNovelopment.domain.NovelBoardId;
import com.yoon.relayNovelopment.domain.Title;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class FakeIdGenerator implements IdGenerator {

    private final String id;

    @Override
    public String getId() {
        return id;
    }

    @Override
    public String getKey(NovelBoardId novelBoardId, Title title) {
        return id;
    }
}
