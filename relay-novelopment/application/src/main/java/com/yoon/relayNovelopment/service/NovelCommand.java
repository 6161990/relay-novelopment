package com.yoon.relayNovelopment.service;

import com.yoon.relayNovelopment.domain.Content;
import com.yoon.relayNovelopment.domain.Genre;
import com.yoon.relayNovelopment.domain.Title;
import com.yoon.relayNovelopment.domain.WriterId;

public interface NovelCommand {

    WriterId getWriterId();
    Title getTitle();
    Content getContent();

    Genre getGenre();

}
