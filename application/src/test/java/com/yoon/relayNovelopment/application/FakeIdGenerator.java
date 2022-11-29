package com.yoon.relayNovelopment.application;

import com.yoon.relayNovelopment.config.IdGenerator;
import com.yoon.relayNovelopment.domain.NovelBoard;
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
    public String getKey(NovelBoard novelBoard, Title title) {
        return id;
    }
}
