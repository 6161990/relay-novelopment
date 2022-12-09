package com.yoon.relayNovelopment.application;

import com.yoon.relayNovelopment.domain.NovelBoard;
import com.yoon.relayNovelopment.domain.NovelBoardId;
import com.yoon.relayNovelopment.service.NovelBoardEditor;
import com.yoon.relayNovelopment.service.NovelEditorCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

@RestController
@RequiredArgsConstructor
public class NovelBoardEditorController {

    @Autowired
    private final NovelBoardEditor novelBoardEditor;

    @PostMapping("/relay/{id}")
    public ResponseEntity<?> relay(@PathVariable("id") String id,
                         @Valid @RequestBody NovelEditorCommand novelEditorCommand) throws URISyntaxException {
        novelBoardEditor.relay(novelEditorCommand);
        URI location = new URI("/novelBoard/"+id);
        return ResponseEntity.created(location).body("{}");
    }

    @PostMapping("/fork/{id}")
    public ResponseEntity<?> fork(@PathVariable("id") String id,
                                          @Valid @RequestBody NovelEditorCommand novelEditorCommand) throws URISyntaxException {
        novelBoardEditor.fork(novelEditorCommand);
        URI location = new URI("/novelBoard/"+id);
        return ResponseEntity.created(location).body("{}");
    }

    @DeleteMapping("/remove/{id}")
    public String remove(@PathVariable("id") NovelBoardId id){
        novelBoardEditor.remove(id);
        return "{}";
    }
}
