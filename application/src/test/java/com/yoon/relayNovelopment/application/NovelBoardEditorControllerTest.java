package com.yoon.relayNovelopment.application;

import com.yoon.relayNovelopment.service.NovelBoardEditor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(NovelBoardEditorController.class)
class NovelBoardEditorControllerTest {

    @Autowired
    MockMvc mvc;

    @MockBean
    private NovelBoardEditor novelBoardEditor;

    @MockBean
    private CommandFactory commandFactory;

    @Test
    void add() {

    }

    @Test
    void remove() {

    }
}