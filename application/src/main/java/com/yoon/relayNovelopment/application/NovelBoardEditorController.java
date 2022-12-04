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

@RestController
@RequiredArgsConstructor
public class NovelBoardEditorController {

    // TODO commandConverter 와 command 와 service 를 어떻게 분리하지?

    @Autowired
    private final NovelBoardEditor novelBoardEditor;

    @PatchMapping("/addNovel/{id}")
    public ResponseEntity<NovelBoard> update(@PathVariable("id") String id,
                         @Valid @RequestBody NovelEditorCommand novelEditorCommand){
        return null;
    }

    @DeleteMapping("/remove/{id}")
    public NovelBoard remove(@PathVariable("id") String id){
        return null;
    }
}
