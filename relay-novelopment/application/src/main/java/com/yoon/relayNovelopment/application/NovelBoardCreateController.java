package com.yoon.relayNovelopment.application;

import com.yoon.relayNovelopment.service.NovelBoardCreator;
import com.yoon.relayNovelopment.service.NovelCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

@RestController
@RequiredArgsConstructor
public class NovelBoardCreateController {

    private final NovelBoardCreator novelBoardCreator;
    private final CommandFactory commandFactory;

   /** 소설 보드 생성 api */
    @PostMapping("/create/novelBoard")
    public ResponseEntity<?> create(@Valid @RequestBody NovelCreateRequest request) throws URISyntaxException {
        // TODO : admin 만 허용하도록 수정
        NovelCommand command = commandFactory.createBy(request);
        novelBoardCreator.create(command);

        URI location = new URI("/novelBoards/");
        return ResponseEntity.created(location).body("{}");
    }

}
