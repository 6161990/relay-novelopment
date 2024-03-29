package com.yoon.relayNovelopment.application;

import com.yoon.relayNovelopment.service.NovelBoardCreator;
import com.yoon.relayNovelopment.service.ArticleCommand;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(NovelBoardCreateController.class)
class ArticleBoardCreateControllerTest {

    @Autowired
    MockMvc mvc;
    @MockBean
    private NovelBoardCreator novelBoardCreator;

    @MockBean
    private CommandFactory commandFactory;

    @Mock
    private ArticleCommand command;

    @Test
    public void createWithValidData() throws Exception {
        given(commandFactory.createBy(new NovelCreateRequest("writer1", "나주에 대하여", "나주는 수언의 고향이었다.", "NOVEL")))
                .willReturn(command);

        mvc.perform(post("/create/novelBoard")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content("{\"writerId\":\"writer1\",\"title\":\"나주에 대하여\", \"content\":\"나주는 수언의 고향이었다.\",  \"genre\":\"NOVEL\"}"))
                .andExpect(status().isCreated())
                .andExpect(header().string("location", "/novelBoards/"))
                .andExpect(content().string("{}"));

        verify(novelBoardCreator).create(any());
    }

    @Test
    void createWithInValidData() throws Exception {
        mvc.perform(post("/create/novelBoard")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"writerId\":\"\",\"title\":\"나주에 대하여\", \"content\":\"content\"}"))
                .andExpect(status().isBadRequest());
    }
}