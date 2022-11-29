package com.yoon.relayNovelopment.config;

import com.yoon.relayNovelopment.domain.NovelBoard;
import com.yoon.relayNovelopment.domain.Title;

public interface IdGenerator {

    String getId();

    String getKey(NovelBoard novelBoard, Title title);
}
