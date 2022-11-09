package com.yoon.relayNovelopment.domain;

import lombok.Value;

@Value(staticConstructor = "of")
public class Opening implements Novel {
    OpeningId id; // FIXME 필요한가?
    WriterId writerId; // FIXME 필요한가?
    Title title; // FIXME 필요한가?
    Content content; // FIXME contentId 와 content 중에 무엇을 넣을까.
    Props props;
}
