package com.yoon.relayNovelopment.domain;

import lombok.Value;

@Value(staticConstructor = "of")
public class NextNovel implements Novel{
    NovelId id;
    WriterId writerId;
    Title title;
    Contents contents;
    Props props;
    boolean isRelay;

    public static NextNovel of(NovelId id, WriterId writerId, Title title, Contents contents, Props props) {
        return new NextNovel(id, writerId, title, contents, props, false);
    }

    private NextNovel(NovelId id, WriterId writerId, Title title, Contents contents, Props props, boolean isRelay) {
        this.id = id;
        this.writerId = writerId;
        this.title = title;
        this.contents = contents;
        this.props = props;
        this.isRelay = isRelay;
    }

}
