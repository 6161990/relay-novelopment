package com.yoon.relayNovelopment.domain;

import lombok.Value;

@Value(staticConstructor = "of")
public class Novel {
    RelayNovelId id;
    NovelId parentNovelId;
    WriterId writerId;
    Title title;
    Contents contents;
    Props props;
    boolean isRelay;

    public static Novel of(RelayNovelId id, NovelId parentNovelId, WriterId writerId, Title title, Contents contents, Props props) {
        return new Novel(id, parentNovelId, writerId, title, contents, props, false);
    }

    private Novel(RelayNovelId id, NovelId parentNovelId, WriterId writerId, Title title, Contents contents, Props props, boolean isRelay) {
        this.id = id;
        this.parentNovelId = parentNovelId;
        this.writerId = writerId;
        this.title = title;
        this.contents = contents;
        this.props = props;
        this.isRelay = isRelay;
    }

}
