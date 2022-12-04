package com.yoon.relayNovelopment.application;

import com.yoon.relayNovelopment.domain.NovelBoard;
import com.yoon.relayNovelopment.service.NovelBoardCreator;
import com.yoon.relayNovelopment.service.NovelCreateCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

@RestController
@RequiredArgsConstructor
public class NovelBoardCreatorController {

    @Autowired
    private final NovelBoardCreator novelBoardCreator;

    @PostMapping("/create/novelBoard")
    public ResponseEntity<?> create(@RequestBody NovelCreateCommand command) throws URISyntaxException {
        novelBoardCreator.create(command);
        URI location = new URI("/novelBoards/");
        return ResponseEntity.created(location).body("{}");
    }

}
