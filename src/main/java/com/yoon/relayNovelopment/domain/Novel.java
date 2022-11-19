package com.yoon.relayNovelopment.domain;

import lombok.Value;

@Value(staticConstructor = "of")
public class Novel {
    RelayNovelId id;
    NovelId parentNovelId;
    WriterId writerId;
    Title title;
    Content content;
    Props props;
    boolean isRelay;

    public static Novel of(RelayNovelId id, NovelId parentNovelId, WriterId writerId, Title title, Content content, Props props) {
        return new Novel(id, parentNovelId, writerId, title, content, props, false);
    }

    private Novel(RelayNovelId id, NovelId parentNovelId, WriterId writerId, Title title, Content content, Props props, boolean isRelay) {
        this.id = id;
        this.parentNovelId = parentNovelId;
        this.writerId = writerId;
        this.title = title;
        this.content = content;
        this.props = props;
        this.isRelay = isRelay;
    }

}
