package com.yoon.relayNovelopment.application;

import com.yoon.relayNovelopment.domain.NovelBoardId;
import com.yoon.relayNovelopment.service.NovelBoardEditor;
import com.yoon.relayNovelopment.service.NovelCommand;
import com.yoon.relayNovelopment.service.NovelEditorCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

@RestController
@RequiredArgsConstructor
public class NovelBoardEditorController {

    // FIXME : controller 는 facade 객체와 협력해야한다. command 를 넘기고 파사드가 novelBoardCreator 와 협력해야한다.
    private final NovelBoardEditor novelBoardEditor;
    private final CommandFactory commandFactory;

    @PatchMapping("/relay/{id}")
    public ResponseEntity<?> relay(@PathVariable("id") NovelBoardId id,
                                   @Valid @RequestBody NovelCreateRequest novelCreateRequest) throws URISyntaxException {

        NovelCommand novelEditorCommand = commandFactory.createBy(id, novelCreateRequest);
        novelBoardEditor.relay((NovelEditorCommand) novelEditorCommand);

        URI location = new URI("/novelBoard/"+ id);
        return ResponseEntity.created(location).body("{}");
    }

    @PatchMapping("/fork/{id}")
    public ResponseEntity<?> fork(@PathVariable("id") NovelBoardId id,
                                  @Valid @RequestBody NovelCreateRequest novelCreateRequest) throws URISyntaxException {

        NovelCommand novelEditorCommand = commandFactory.createBy(id, novelCreateRequest);
        novelBoardEditor.fork((NovelEditorCommand) novelEditorCommand);

        URI location = new URI("/novelBoard/"+ id);
        return ResponseEntity.created(location).body("{}");
    }

    @DeleteMapping("/remove/{id}")
    public String remove(@PathVariable("id") NovelBoardId id){
        novelBoardEditor.remove(id);
        return "{}";
    }
}
