package com.yoon.relayNovelopment.domain;

import lombok.Value;

@Value(staticConstructor = "of")
public class RelayNovelId implements NovelId {
    String id;
}
