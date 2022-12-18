package com.yoon.relayNovelopment.persist;

import com.yoon.relayNovelopment.domain.Novel;
import lombok.AccessLevel;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Data
@RequiredArgsConstructor(access = AccessLevel.MODULE)
public class NovelsWrapperForPersist {

    private final List<Novel> novels;

    public static NovelsWrapperForPersist of(List<Novel> novels) {
        return new NovelsWrapperForPersist(novels);
    }
}
