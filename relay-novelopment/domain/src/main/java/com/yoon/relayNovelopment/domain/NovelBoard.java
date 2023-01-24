package com.yoon.relayNovelopment.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Objects;

import static org.valid4j.Validation.validate;
@Getter
@AllArgsConstructor
public class NovelBoard {

    private final NovelBoardId novelBoardId;
    private Opening opening;
    private boolean isClosed;
    private Articles articles;
    private LocalDateTime createdAt;
    private LocalDateTime deleteAt;
    private Long version;

    public NovelBoard(NovelBoardId novelBoardId, Opening opening) {
        this.novelBoardId = novelBoardId;
        this.opening = opening;
    }

    public void relay(Article article) {
        if (articles == null || articles.isEmpty()){
            init();
        }

        valid(article);

        this.articles.add(article);
    }

    public void fork(Article article) {
        validForFork(article);

        this.articles.add(article);
    }

    // TODO : test
    public void editOpening(OpeningKey openingKey, WriterId writerId, Title title, Content content) {
        this.opening = Opening.of(openingKey, writerId, title, content);
    }

    public void close() {
        this.isClosed = true;
    }

    public int getNovelSize(){
        return this.articles == null ? 0 : this.articles.size();
    }

    private void init() {
        this.articles = new Articles(new ArrayList<>());
    }

    private void validForFork(Article article){
        validate(Objects.nonNull(articles), new NovelBoardException("Novels is Empty."));
        validate(articles.existSameParent(article.getParentArticleKey()), new NovelBoardException("Not Exist Same Parent Novel."));
        valid(article);
    }

    private void valid(Article article) {
        validate(articles.isNotExist(article.getWriterId()), new NovelBoardException(String.format("Already exist the writer. WriterId %s, NovelBoardId %s", article.getWriterId(), novelBoardId)));
        validate(articles.isNotExist(article.getTitle()),  new NovelBoardException(String.format("Already exist the title. WriterId %s, NovelBoardId %s", article.getWriterId(), novelBoardId)));
        validate(!isClosed, new NovelBoardException("Already closed."));
    }

}
