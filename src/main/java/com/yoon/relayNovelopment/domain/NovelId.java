package com.yoon.relayNovelopment.domain;

public interface NovelId {
    static NovelId relayNovelIdOf(String id) {
        return RelayNovelId.of(id);
    }

    static NovelId openingIdOf(String id) {
        return OpeningId.of(id);
    }
}
