package com.yoon.relayNovelopment.service;

import com.yoon.relayNovelopment.domain.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;

import static org.valid4j.Validation.validate;

@Service
@RequiredArgsConstructor
public class NovelBoardEditor {

    private final NovelBoardRepository repository;
    private final ArticleCreateFactory createFactory;

    public void relay(NovelBoardEditCommand command) {
        NovelBoard novelBoard = repository.findBy(command.getNovelBoardId());
        validNonNull(command, novelBoard);

        Article article = createFactory.createForRelay(novelBoard, command); // create - relay
        novelBoard.relay(article);

        repository.save(novelBoard);
    }

    public void fork(NovelBoardEditCommand command){
        NovelBoard novelBoard = repository.findBy(command.getNovelBoardId());
        validNonNull(command, novelBoard);

        Article article = createFactory.createForFork(novelBoard, command); // createForFork - fork
        novelBoard.fork(article);

        repository.save(novelBoard);
    }

    public void close(NovelBoardId id) {
        NovelBoard novelBoard = repository.findBy(id);

        novelBoard.close();
    }

    // TODO : test
    public void edit(NovelBoardEditCommand command) {
        NovelBoard novelBoard = repository.findBy(command.getNovelBoardId());
        validNonNull(command, novelBoard);

        Opening opening = novelBoard.getOpening();
        // TODO : test validate
        validate(command.getWriterId().equals(novelBoard.getOpening().getWriterId()), new NovelBoardException(String.format("WriterId Not Equals. WriterId is %s.", command.getWriterId())));

        novelBoard.editOpening(opening.getOpeningKey(), opening.getWriterId(), command.getTitle(), command.getContent());

        repository.save(novelBoard);
    }

    public void remove(NovelBoardId id) {
        repository.delete(id);
    }

    private void validNonNull(NovelBoardEditCommand command, NovelBoard novelBoard) {
        validate(Objects.nonNull(novelBoard), new NovelBoardException(String.format("This NovelBoard Not Found. NovelBoardId is %s.", command.getNovelBoardId())));
    }

}
