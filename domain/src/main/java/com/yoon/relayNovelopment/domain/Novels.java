package com.yoon.relayNovelopment.domain;

import lombok.RequiredArgsConstructor;

import java.util.List;
@RequiredArgsConstructor
public class Novels {

    private final List<Novel> novels;

    boolean isEmpty() {
        return novels.isEmpty();
    }

    void add(Novel novel) {
        novels.add(novel);
    }

    int getSameParentSizeBy(NovelKey id) {
        return (int) novels.stream().filter(i -> i.getParentNovelKey().equals(id)).count();
    }

    int size() {
        return novels.size();
    }

    boolean existSameParent(NovelKey parentNovelKey) {
        return novels.stream().anyMatch(e->e.getParentNovelKey().equals(parentNovelKey));
    }

    boolean isNotExist(WriterId writerId) {
        return novels.stream().noneMatch(n-> n.getWriterId().getId().equals(writerId.getId()));
    }

    boolean isNotExist(Title title) {
        return novels.stream().noneMatch(n-> n.getTitle().equals(title));
    }

    Novel get(int index) {
        return novels.get(index);
    }
}
