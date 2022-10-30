package com.yoon.relayNovelopment.domain;

import lombok.Value;

@Value(staticConstructor = "of")
public class Novel {
    NovelId id;
    WriterId writer;
    Title title;
    Contents contents;
    Props props;
}
