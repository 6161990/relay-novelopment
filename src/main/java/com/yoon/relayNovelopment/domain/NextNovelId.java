package com.yoon.relayNovelopment.domain;

import lombok.Value;

@Value(staticConstructor = "of")
public class NextNovelId implements NovelId {
    String id;
}
