package com.yoon.relayNovelopment.application;

import com.yoon.relayNovelopment.service.NovelBoardCreator;
import com.yoon.relayNovelopment.service.NovelCommand;
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

    // FIXME : controller 는 facade 객체와 협력해야한다. command 를 넘기고 파사드가 novelBoardCreator 와 협력해야한다.
    // FIXME : 파사드의 역할이 많아질 것. CommandFactory -> NovelBoardCreator ->  NovelRepository -> IdGenerator
    private final CommandFactory commandFactory;
    private final NovelBoardCreator novelBoardCreator;

    @PostMapping("/create/novelBoard")
    public ResponseEntity<?> create(@Valid @RequestBody NovelCreateRequest request) throws URISyntaxException {
        NovelCommand command = commandFactory.createBy(request);
        novelBoardCreator.create((NovelCreateCommand) command);

        URI location = new URI("/novelBoards/");
        return ResponseEntity.created(location).body("{}");
    }

}
