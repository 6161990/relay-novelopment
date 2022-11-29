package com.yoon.relayNovelopment.domain;

import lombok.Value;

@Value(staticConstructor = "of")
public class RelayNovelKey implements NovelKey {
    String key;
}
