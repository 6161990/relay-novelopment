package com.yoon.relayNovelopment.domain;

import lombok.Value;

@Value(staticConstructor = "of")
public class OpeningNovel implements Novel {
    OpeningNovelId id;
    WriterId writerId;
    Title title;
    Content content; // FIXME contentId 와 content 중에 무엇을 넣을까.
    Props props;
}
