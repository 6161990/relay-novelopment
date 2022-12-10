package com.yoon.relayNovelopment.application;

import com.yoon.relayNovelopment.service.NovelBoardCreator;
import com.yoon.relayNovelopment.service.NovelCreateCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

@RestController
@RequiredArgsConstructor
public class NovelBoardCreatorController {

    private final NovelBoardCreator novelBoardCreator;

    @PostMapping("/create/novelBoard") // TODO : NovelCreateCommand -> NovelCreateRequest => Commandfactory 와 협력해야한다
    public ResponseEntity<?> create(@Valid @RequestBody NovelCreateCommand command) throws URISyntaxException {
        novelBoardCreator.create(command);

        URI location = new URI("/novelBoards/");
        return ResponseEntity.created(location).body("{}");
    }

}
