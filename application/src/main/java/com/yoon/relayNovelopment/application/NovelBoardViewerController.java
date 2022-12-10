package com.yoon.relayNovelopment.application;

import com.yoon.relayNovelopment.domain.NovelBoard;
import com.yoon.relayNovelopment.domain.NovelBoardId;
import com.yoon.relayNovelopment.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class NovelBoardViewerController {

    private final NovelBoardViewer novelBoardViewer;

    @GetMapping("/novelBoard/{id}")
    public NovelBoard detail(@PathVariable("id") NovelBoardId id){
        return novelBoardViewer.findBy(id);
    }

    @GetMapping("/novelBoards")
    public List<NovelBoard> list() {
        return novelBoardViewer.findAll();
    }

}
