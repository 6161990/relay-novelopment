package com.yoon.relayNovelopment.application;

import com.yoon.relayNovelopment.domain.Content;
import com.yoon.relayNovelopment.domain.Title;
import com.yoon.relayNovelopment.domain.WriterId;

public interface NovelCommand {

    WriterId getWriterId();
    Title getTitle();
    Content getContent();

}
