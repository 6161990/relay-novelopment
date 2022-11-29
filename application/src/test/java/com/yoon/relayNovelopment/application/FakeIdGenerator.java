package com.yoon.relayNovelopment.application;

import com.yoon.relayNovelopment.config.IdGenerator;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class FakeIdGenerator implements IdGenerator {

    private final String id;

    @Override
    public String getId() {
        return id;
    }
}
