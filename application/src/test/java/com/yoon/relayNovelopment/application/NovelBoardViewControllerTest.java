package com.yoon.relayNovelopment.application;

import com.yoon.relayNovelopment.domain.*;
import com.yoon.relayNovelopment.service.NovelBoardViewer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(NovelBoardViewerController.class)
class NovelBoardViewControllerTest {

    @Autowired
    MockMvc mvc;

    @MockBean
    private NovelBoardViewer novelBoardViewer;

    @Test
    void detailFindOne() throws Exception {
        NovelBoard novelBoard
                = new NovelBoard(NovelBoardId.of("1004"), Opening.of(OpeningKey.of("openKey"), WriterId.of("writer"), Title.of("title"), Content.of("content")));
        given(novelBoardViewer.findBy(NovelBoardId.of("1004"))).willReturn(novelBoard);

        mvc.perform(get("/novelBoard/1004"))
                .andExpect(status().isOk())
                .andExpect(content().string(
                        containsString("{\"novelBoardId\":{\"id\":\"1004\"}")
                ))
                .andExpect(content().string(
                        containsString("\"opening\":{\"key\":{\"value\":\"openKey\"}")
                ));
    }

    @Test
    void detailFineAll() {

    }

    @Test
    void detailWithNotExisted() {
    }

}