package com.yoon.relayNovelopment.application;

import com.yoon.relayNovelopment.domain.NovelBoardId;
import com.yoon.relayNovelopment.service.NovelBoardEditor;
import com.yoon.relayNovelopment.service.NovelCommand;
import com.yoon.relayNovelopment.service.NovelEditCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

@RestController
@RequiredArgsConstructor
public class NovelBoardEditController {

    private final NovelBoardEditor novelBoardEditor;
    private final CommandFactory commandFactory;

    @PatchMapping("/relay/{id}")
    public ResponseEntity<?> relay(@PathVariable("id") NovelBoardId id,
                                   @Valid @RequestBody NovelCreateRequest novelCreateRequest) throws URISyntaxException {

        NovelCommand novelEditorCommand = commandFactory.createBy(id, novelCreateRequest);
        novelBoardEditor.relay((NovelEditCommand) novelEditorCommand);

        URI location = new URI("/novelBoard/"+ id);
        return ResponseEntity.created(location).body("{}");
    }

    @PatchMapping("/fork/{id}")
    public ResponseEntity<?> fork(@PathVariable("id") NovelBoardId id,
                                  @Valid @RequestBody NovelCreateRequest novelCreateRequest) throws URISyntaxException {

        NovelCommand novelEditorCommand = commandFactory.createBy(id, novelCreateRequest);
        novelBoardEditor.fork((NovelEditCommand) novelEditorCommand);

        URI location = new URI("/novelBoard/"+ id);
        return ResponseEntity.created(location).body("{}");
    }

    // TODO : test
    @PatchMapping("/edit/{id}")
    public ResponseEntity<?> edit(@PathVariable("id") NovelBoardId id,
                                   @Valid @RequestBody NovelCreateRequest novelCreateRequest) throws URISyntaxException {
        // TODO : admin 만 허용하도록 수정
        NovelCommand novelEditorCommand = commandFactory.createBy(id, novelCreateRequest);
        novelBoardEditor.edit((NovelEditCommand) novelEditorCommand);

        URI location = new URI("/novelBoard/"+ id);
        return ResponseEntity.created(location).body("{}");
    }

    @PatchMapping("/close/{id}")
    public ResponseEntity<?> close(@PathVariable("id") NovelBoardId id) throws URISyntaxException {
        // TODO : admin 만 허용하도록 수정
        novelBoardEditor.close(id);

        URI location = new URI("/novelBoard/"+ id);
        return ResponseEntity.ok(location);
    }

    @DeleteMapping("/remove/{id}")
    public String remove(@PathVariable("id") NovelBoardId id){
        novelBoardEditor.remove(id);
        return "{}";
    }
}
