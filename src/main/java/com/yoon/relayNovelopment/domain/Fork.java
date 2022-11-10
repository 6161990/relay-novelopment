package com.yoon.relayNovelopment.domain;

import lombok.Value;

@Value(staticConstructor = "of")
public class Fork {
    ForkId id;
    WriterId writerId;
    Title title;
    Contents contents;
    Props props;
    boolean isRelay;

    public static Fork of(ForkId id, WriterId writerId, Title title, Contents contents, Props props) {
        return new Fork(id, writerId, title, contents, props, false);
    }

    private Fork(ForkId id, WriterId writerId, Title title, Contents contents, Props props, boolean isRelay) {
        this.id = id;
        this.writerId = writerId;
        this.title = title;
        this.contents = contents;
        this.props = props;
        this.isRelay = isRelay;
    }

}
