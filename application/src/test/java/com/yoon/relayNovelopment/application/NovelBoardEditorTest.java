package com.yoon.relayNovelopment.application;

import com.yoon.relayNovelopment.domain.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class NovelBoardEditorTest {

    NovelBoardEditor sut;

    @BeforeEach
    void setUp() {
        sut = new NovelBoardEditor(new FakeNovelRepository(),
                                    new NovelCreateFactory(new IdGeneratore()));
    }

    @Test
    void relay() {
        NovelCreateCommand command = new NovelCreateCommand(WriterId.of("writerId"), Title.of("Title"), Content.of(ContentId.of("conId"), "value"));

        NovelBoard novelBoard = sut.relay(NovelBoardId.of("boardId"), command);

        assertThat(novelBoard.getNovelSize()).isEqualTo(1);
        assertThat(novelBoard.getSameParentSizeBy(OpeningId.of("openId"))).isEqualTo(1);
    }

    @Test
    void fork() {
        NovelCreateCommand command = new NovelCreateCommand(WriterId.of("writerId"), Title.of("Title"), Content.of(ContentId.of("conId"), "value"));
        sut.relay(NovelBoardId.of("boardId"), command);

        NovelCreateCommand command2 = new NovelCreateCommand(WriterId.of("writerId2"), Title.of("Title2"), Content.of(ContentId.of("conId"), "value"));
        NovelBoard novelBoard2 = sut.fork(NovelBoardId.of("boardId"), command2);

        assertThat(novelBoard2.getNovelSize()).isEqualTo(2);
        assertThat(novelBoard2.getSameParentSizeBy(OpeningId.of("openId"))).isEqualTo(2);
    }
}