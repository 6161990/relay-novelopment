package com.yoon.relayNovelopment.domain;

import lombok.RequiredArgsConstructor;

import java.util.List;
@RequiredArgsConstructor
public class Novels {

    private final List<Novel> novels;

    public boolean isEmpty() {
        return novels.isEmpty();
    }

    public void add(Novel novel) {
        novels.add(novel);
    }

    public int getSameParentSizeBy(NovelKey id) {
        return (int) novels.stream().filter(i -> i.getParentNovelKey().equals(id)).count();
    }

    public int size() {
        return novels.size();
    }

    public boolean existSameParent(NovelKey parentNovelKey) {
        return novels.stream().anyMatch(e->e.getParentNovelKey().equals(parentNovelKey));
    }

    public boolean isNotExist(WriterId writerId) {
        return novels.stream().noneMatch(n-> n.getWriterId().getId().equals(writerId.getId()));
    }

    public boolean isNotExist(Title title) {
        return novels.stream().noneMatch(n-> n.getTitle().equals(title));
    }

    public Novel get(int index) {
        return novels.get(index);
    }
}
