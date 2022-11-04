package com.yoon.relayNovelopment.domain;

import lombok.Value;

@Value(staticConstructor = "of")
public class OpeningNovelId implements NovelId {
    String id;
}
