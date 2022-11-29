package com.yoon.relayNovelopment.domain;

import lombok.Value;

@Value(staticConstructor = "of")
public class OpeningKey implements NovelKey {
    String id;
}
