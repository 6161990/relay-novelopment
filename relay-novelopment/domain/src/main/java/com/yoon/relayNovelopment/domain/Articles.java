package com.yoon.relayNovelopment.domain;

import lombok.RequiredArgsConstructor;

import java.util.List;
@RequiredArgsConstructor
public class Articles {

    private final List<Article> articles;

    boolean isEmpty() {
        return articles.isEmpty();
    }

    void add(Article article) {
        articles.add(article);
    }

    int getSameParentSizeBy(ArticleKey id) {
        return (int) articles.stream().filter(i -> i.getParentArticleKey().equals(id)).count();
    }

    int size() {
        return articles.size();
    }

    boolean existSameParent(ArticleKey parentArticleKey) {
        return articles.stream().anyMatch(e->e.getParentArticleKey().equals(parentArticleKey));
    }

    boolean isNotExist(WriterId writerId) {
        return articles.stream().noneMatch(n-> n.getWriterId().getId().equals(writerId.getId()));
    }

    boolean isNotExist(Title title) {
        return articles.stream().noneMatch(n-> n.getTitle().equals(title));
    }

    Article get(int index) {
        return articles.get(index);
    }
}
