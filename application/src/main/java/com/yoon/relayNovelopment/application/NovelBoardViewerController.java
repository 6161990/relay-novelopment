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

    // TODO Creator , Editor 와 맞추려고 조회용 service 레이어를 만들긴 했는데 바로 repository 조회하는 게 더 낫지 않으려나?

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
