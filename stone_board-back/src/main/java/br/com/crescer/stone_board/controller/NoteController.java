package br.com.crescer.stone_board.controller;

import br.com.crescer.stone_board.entity.Person;
import br.com.crescer.stone_board.entity.model.NoteModel;
import br.com.crescer.stone_board.service.NoteService;
import br.com.crescer.stone_board.utils.PersonComponent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Marcele Dorneles
 */
@RestController
@RequestMapping("/note")
public class NoteController {

    @Autowired
    private NoteService noteService;

    @Autowired
    private PersonComponent personComponent;

    @PostMapping
    public void save(@Validated @RequestBody NoteModel noteModel) {
        Person person = personComponent.loggedPersonDetails();
        noteService.save(noteModel, person);
    }

    @PutMapping
    public void update(@RequestBody NoteModel noteModel) {
        Person person = personComponent.loggedPersonDetails();
        noteService.update(noteModel,person);
    }

}
