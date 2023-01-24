package com.yoon.relayNovelopment.domain;

import lombok.Value;

import java.util.Objects;

import static org.valid4j.Validation.validate;

@Value(staticConstructor = "of")
public class Article {
    RelayArticleKey relayNovelKey;
    ArticleKey parentArticleKey;
    WriterId writerId;
    Title title;
    Content content;
    Genre genre; // FIXME article 에 장르가 필요할까? 장르 간 구별은 어떻게 하나? 소설 <=> 로맨스

    private Article(RelayArticleKey relayNovelKey, ArticleKey parentArticleKey, WriterId writerId, Title title, Content content, Genre genre) {
        preValid(relayNovelKey, parentArticleKey, writerId, title, content);
        this.relayNovelKey = relayNovelKey;
        this.parentArticleKey = parentArticleKey;
        this.writerId = writerId;
        this.title = title;
        this.content = content;
        this.genre = genre;
    }

    private void preValid(RelayArticleKey novelKey, ArticleKey parentArticleKey, WriterId writerId, Title title, Content content) {
        validate(Objects.nonNull(novelKey), new NovelBoardException("NovelKey is Null."));
        validate(Objects.nonNull(parentArticleKey), new NovelBoardException("ParentNovelKey is Null."));
        validate(Objects.nonNull(writerId), new NovelBoardException("WriterId is Null."));
        validate(Objects.nonNull(title), new NovelBoardException("Title is Null."));
        validate(Objects.nonNull(content), new NovelBoardException("Content is Null."));
    }

}
