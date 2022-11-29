package com.yoon.relayNovelopment.config;

import com.yoon.relayNovelopment.domain.NovelBoard;
import com.yoon.relayNovelopment.domain.NovelBoardId;
import com.yoon.relayNovelopment.domain.Title;
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

    @Override
    public String getKey(NovelBoardId novelBoardId, Title title) {
        return novelBoardId.getId().concat(":").concat(title.getName());
    }
}
