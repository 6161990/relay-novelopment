package com.yoon.relayNovelopment.application;

import com.yoon.relayNovelopment.service.NovelBoardCreator;
import com.yoon.relayNovelopment.service.NovelCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

@RestController
@RequiredArgsConstructor
public class NovelBoardCreateController {

    private final NovelBoardCreator novelBoardCreator;
    private final CommandFactory commandFactory;

    @PostMapping("/create/novelBoard")
    public ResponseEntity<?> create(@Valid @RequestBody NovelCreateRequest request) throws URISyntaxException {
        NovelCommand command = commandFactory.createBy(request);
        novelBoardCreator.create(command);

        URI location = new URI("/novelBoards/");
        return ResponseEntity.created(location).body("{}");
    }

}
