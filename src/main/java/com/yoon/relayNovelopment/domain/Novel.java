package com.yoon.relayNovelopment.domain;

import lombok.Value;

@Value(staticConstructor = "of")
public class Novel {
    RelayNovelId id;
    NovelId parentNovelId;
    WriterId writerId;
    Title title;
    Content content;

    private Novel(RelayNovelId id, NovelId parentNovelId, WriterId writerId, Title title, Content content) {
        this.id = id;
        this.parentNovelId = parentNovelId;
        this.writerId = writerId;
        this.title = title;
        this.content = content;
    }

}
