package com.yoon.relayNovelopment.application;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.AlternativeJdkIdGenerator;

@Service
@RequiredArgsConstructor
public class DefaultIdGenerator implements IdGenerator {

    private final AlternativeJdkIdGenerator alternativeJdkIdGenerator;

    @Override
    public String getId() {
        return alternativeJdkIdGenerator.generateId().toString();
    }
}
