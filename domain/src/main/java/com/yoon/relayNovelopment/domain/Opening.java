package com.yoon.relayNovelopment.domain;

import lombok.Value;

@Value(staticConstructor = "of")
public class Opening  {
    OpeningKey key;
    WriterId writerId;
    Title title;
    Content content; // FIXME contentId 와 content 중에 무엇을 넣을까.
}
