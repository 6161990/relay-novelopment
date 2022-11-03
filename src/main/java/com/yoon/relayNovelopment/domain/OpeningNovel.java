package com.yoon.relayNovelopment.domain;

import lombok.Value;

@Value(staticConstructor = "of")
public class OpeningNovel {
    OpeningNovelId id;
    // ContentId contentId; // FIXME thinking 컨텐츠는 해당 도메인에서 얼마나 중요한가. 소설이 중요한가 콘텐츠가 중요한가?
    Content content; // FIXME contentId 와 content 중에 무엇을 넣을까.
}
