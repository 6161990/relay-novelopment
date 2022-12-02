package com.yoon.relayNovelopment.application;

import com.yoon.relayNovelopment.domain.NovelBoard;
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
public class NovelBoardController {

    @Autowired
    private final NovelBoardCreator novelBoardCreator;
    @Autowired
    private final NovelBoardEditor novelBoardEditor;

    @GetMapping("/novelBoard/{id}")
    public NovelBoard detail(@PathVariable("id")String id){
        return null;
    }

    @GetMapping("/novelBoards")
    public List<NovelBoard> list() {

        return null;
    }

    @PostMapping("/create/novelBoard")
    public ResponseEntity<?> create(@RequestBody NovelCreateCommand command){

        return null;
    }

    @PatchMapping("/addNovel/{id}")
    public ResponseEntity<NovelBoard> update(@PathVariable("id") String id,
                         @Valid @RequestBody NovelEditorCommand novelEditorCommand){
        return null;
    }

    @PatchMapping("/removeNovelBoard/{id}")
    public NovelBoard remove(@PathVariable("id") String id){
        return null;
    }
}
