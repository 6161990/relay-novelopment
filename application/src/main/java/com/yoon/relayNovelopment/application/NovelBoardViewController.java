package com.yoon.relayNovelopment.application;

import com.yoon.relayNovelopment.domain.NovelBoard;
import com.yoon.relayNovelopment.domain.NovelRepository;
import com.yoon.relayNovelopment.service.NovelBoardCreator;
import com.yoon.relayNovelopment.service.NovelBoardEditor;
import com.yoon.relayNovelopment.service.NovelCreateCommand;
import com.yoon.relayNovelopment.service.NovelEditorCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class NovelBoardViewController {

    private final NovelRepository repository;

    @GetMapping("/novelBoard/{id}")
    public NovelBoard detail(@PathVariable("id")String id){
        return null;
    }

    @GetMapping("/novelBoards")
    public List<NovelBoard> list() {

        return null;
    }

}
