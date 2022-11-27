package com.yoon.relayNovelopment.application;

import com.yoon.relayNovelopment.domain.NovelBoard;
import org.springframework.stereotype.Service;

@Service
public class IdGeneratore {

    public String gen() {
        return "novelBoardId";
    }

    public String keyOpen(){
        return "open";
    }

    public String key(NovelBoard novelBoard){
        String id = novelBoard.getId().getId();
        if(novelBoard.getNovelSize() == 0){
            return id.concat(":1");
        }else {
            return id.concat(":".concat(String.valueOf(novelBoard.getNovelSize()+1)));
        }
        // NovelBoardId 와 노드 순서로 이루어져있다? ex) 2022_novelBoardId:1
        // 아니면 title ?
    }

    public String keyForFork(NovelBoard novelBoard){
        String id = novelBoard.getId().getId();
        return id.concat(":".concat(String.valueOf(novelBoard.getNovelSize())));
        // NovelBoardId 와 노드 순서로 이루어져있다. ex) 2022_novelBoardId:1:fork1 // FIXME fork key 를 좀 더 생각해보자
    }
}
