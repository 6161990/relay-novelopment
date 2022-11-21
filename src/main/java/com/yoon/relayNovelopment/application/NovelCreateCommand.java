package com.yoon.relayNovelopment.application;

import com.yoon.relayNovelopment.domain.Content;
import com.yoon.relayNovelopment.domain.Props;
import com.yoon.relayNovelopment.domain.Title;
import com.yoon.relayNovelopment.domain.WriterId;
import lombok.Getter;

@Getter
public class NovelCreateCommand {

    private WriterId writerId;
    private Title title;
    private Content content;
    private Props props;

    public NovelCreateCommand(WriterId writerId, Title title, Content content, Props props) {
        this.writerId = writerId;
        this.title = title;
        this.content = content;
        this.props = props;
    }
}
