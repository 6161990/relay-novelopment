package com.yoon.relayNovelopment.domain;

import lombok.Value;

@Value(staticConstructor = "of")
public class Opening  {
    OpeningKey key;
    WriterId writerId;
    Title title;
    Content content;
}
