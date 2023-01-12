package com.yoon.relayNovelopment.config;

import com.yoon.relayNovelopment.domain.NovelBoardId;
import com.yoon.relayNovelopment.domain.Title;

public interface IdGenerator {

    String getId();

    String getKey(NovelBoardId novelBoardId, Title title);
}
