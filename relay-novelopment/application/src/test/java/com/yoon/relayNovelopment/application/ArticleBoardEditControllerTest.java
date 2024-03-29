package com.yoon.relayNovelopment.application;

import com.yoon.relayNovelopment.domain.NovelBoardId;
import com.yoon.relayNovelopment.service.NovelBoardEditor;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(NovelBoardEditController.class)
class ArticleBoardEditControllerTest {

    @Autowired
    MockMvc mvc;

    @MockBean
    private NovelBoardEditor novelBoardEditor;

    @MockBean
    private CommandFactory commandFactory;

    @Mock
    private ArticleCommand command;

    @Test
    void relayValidData() throws Exception {
        given(commandFactory.createBy(new NovelCreateRequest("writer1", "나주에 대하여", "나주는 수언의 고향이었다.", "NOVEL")))
                .willReturn(command);

        mvc.perform(patch("/relay/1004")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"writerId\":\"writer1\",\"title\":\"나주에 대하여\", \"content\":\"나주는 수언의 고향이었다.\",  \"genre\":\"NOVEL\"}"))
                .andExpect(status().isCreated());

        verify(novelBoardEditor).relay(any());
    }

    @Test
    void relayInValidData() throws Exception {
        mvc.perform(patch("/relay/1004")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"writerId\":\"writer1\", \"content\":\"\"}"))
                .andExpect(status().isBadRequest());
    }

    @Test
    void close() throws Exception {
        mvc.perform(patch("/close/1004"))
                .andExpect(status().isOk());

        verify(novelBoardEditor).close(NovelBoardId.of("1004"));
    }

    @Test
    void remove() throws Exception {
        mvc.perform(delete("/remove/1004"))
                .andExpect(status().isOk());

        verify(novelBoardEditor).remove(NovelBoardId.of("1004"));
    }


}