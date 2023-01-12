package com.yoon.relayNovelopment.application;

import com.yoon.relayNovelopment.domain.*;
import com.yoon.relayNovelopment.service.NovelBoardNotFoundException;
import com.yoon.relayNovelopment.service.NovelBoardViewer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(NovelBoardViewController.class)
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
                        containsString("\"opening\":{\"openingKey\":{\"key\":\"openKey\"}")
                ));
    }

    @Test
    void detailFineAll() throws Exception {
        NovelBoard novelBoard
                = new NovelBoard(NovelBoardId.of("1004"), Opening.of(OpeningKey.of("openKey"), WriterId.of("writer"), Title.of("title"), Content.of("content")));
        NovelBoard novelBoard1
                = new NovelBoard(NovelBoardId.of("1005"), Opening.of(OpeningKey.of("openKey1"), WriterId.of("writer1"), Title.of("title1"), Content.of("content1")));
        given(novelBoardViewer.findAll()).willReturn(List.of(novelBoard, novelBoard1));

        mvc.perform(get("/novelBoards"))
                .andExpect(status().isOk())
                .andExpect(content().string(
                        containsString("{\"novelBoardId\":{\"id\":\"1004\"}")
                ))
                .andExpect(content().string(
                        containsString("{\"novelBoardId\":{\"id\":\"1005\"}")
                ));
    }

    @Test
    void detailWithNotExisted() throws Exception {
        given(novelBoardViewer.findBy(NovelBoardId.of("404")))
                .willThrow(new NovelBoardNotFoundException("This NovelBoard Not Found. NovelBoardId is 404."));

        mvc.perform(get("/novelBoard/404"))
                .andExpect(status().isNotFound())
                .andExpect(content().string("{}"));
    }

}