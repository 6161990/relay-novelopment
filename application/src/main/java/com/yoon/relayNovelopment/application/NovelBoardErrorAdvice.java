package com.yoon.relayNovelopment.application;

import com.yoon.relayNovelopment.service.NovelBoardNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class NovelBoardErrorAdvice {

    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NovelBoardNotFoundException.class)
    public String handlerNotFound(){
        return "{}";
    }
}
