package com.yoon.relayNovelopment.service;

import com.yoon.relayNovelopment.domain.Content;
import com.yoon.relayNovelopment.domain.Title;
import com.yoon.relayNovelopment.domain.WriterId;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.Valid;

@Data
@AllArgsConstructor
public class NovelCreateCommand implements NovelCommand {

    @Valid
    WriterId writerId;

    @Valid
    Title title;

    @Valid
    Content content;

}
