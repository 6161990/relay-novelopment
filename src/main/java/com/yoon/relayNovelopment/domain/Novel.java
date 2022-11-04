package com.yoon.relayNovelopment.domain;

import lombok.Value;

@Value(staticConstructor = "of")
public class Novel {
    NovelId id;
    WriterId writer;
    Title title;
    Contents contents;
    Props props;
    boolean isRelay;

    public static Novel of(NovelId id, WriterId writer, Title title, Contents contents, Props props) {
        return new Novel(id, writer, title, contents, props, false);
    }

    private Novel(NovelId id, WriterId writer, Title title, Contents contents, Props props, boolean isRelay) {
        this.id = id;
        this.writer = writer;
        this.title = title;
        this.contents = contents;
        this.props = props;
        this.isRelay = isRelay;
    }

}
