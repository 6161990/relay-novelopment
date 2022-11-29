package com.yoon.relayNovelopment.domain;

import lombok.Value;

@Value(staticConstructor = "of")
public class Novel {
    RelayNovelKey id;
    NovelKey parentNovelKey;
    WriterId writerId;
    Title title;
    Content content;

    private Novel(RelayNovelKey id, NovelKey parentNovelKey, WriterId writerId, Title title, Content content) {
        this.id = id;
        this.parentNovelKey = parentNovelKey;
        this.writerId = writerId;
        this.title = title;
        this.content = content;
    }

}
