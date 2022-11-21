package com.yoon.relayNovelopment.application;

import com.yoon.relayNovelopment.domain.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class NovelBoardEditorTest {

    NovelBoardEditor sut;

    @BeforeEach
    void setUp() {
        sut = new NovelBoardEditor(
                new NovelRepository() {
                    @Override
                    public void save(NovelBoard novelBoard) {

                    }

                    @Override
                    public NovelBoard findBy(NovelBoardId novelBoardId) {
                        return new NovelBoard(NovelBoardId.of("boardId"), Opening.of(OpeningId.of("openId"), WriterId.of("admin"), Title.of("title") , Content.of(ContentId.of("id"), "value")));
                    }
                }
                , new NovelCreateFactory(new IdGenerator(), new ParentFinder()));
    }

    @Test
    void name() {
        NovelEditCommand command = new NovelEditCommand(WriterId.of("writerId"), Title.of("Title"), Content.of(ContentId.of("conId"), "value"), new Props());

        NovelBoard novelBoard = sut.relay(NovelBoardId.of("boardId"), command);

        assertThat(novelBoard.getNovelSize()).isEqualTo(1);
        assertThat(novelBoard.getSameParentSizeBy(OpeningId.of("openId"))).isEqualTo(1);
    }
}