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

    // FIXME : controller 는 facade 객체와 협력해야한다. command 를 넘기고 파사드가 novelBoardCreator 와 협력해야한다.
    private final NovelBoardCreator novelBoardCreator;
    private final CommandFactory commandFactory;

    @PostMapping("/create/novelBoard")
    public ResponseEntity<?> create(@Valid @RequestBody NovelCreateRequest request) throws URISyntaxException {
        NovelCreateCommand command = commandFactory.create(request);
        novelBoardCreator.create(command);

        URI location = new URI("/novelBoards/");
        return ResponseEntity.created(location).body("{}");
    }

}
