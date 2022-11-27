package com.yoon.relayNovelopment.domain;

import lombok.Value;

@Value(staticConstructor = "of")
public class OpeningId implements NovelId {
    String id;
}
