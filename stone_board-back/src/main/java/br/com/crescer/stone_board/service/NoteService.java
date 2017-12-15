/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.crescer.stone_board.service;

import br.com.crescer.stone_board.entity.Card;
import br.com.crescer.stone_board.entity.Note;
import br.com.crescer.stone_board.entity.Person;
import br.com.crescer.stone_board.entity.model.NoteModel;
import br.com.crescer.stone_board.repository.CardRepository;
import br.com.crescer.stone_board.repository.NoteRepository;
import br.com.crescer.stone_board.repository.PersonRepository;
import br.com.crescer.stone_board.utils.BadRequestException;
import br.com.crescer.stone_board.utils.PersonComponent;
import java.util.stream.Collectors;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author willian
 */
@Service
public class NoteService {
           
    @Autowired
    NoteRepository noteRepository;
    @Autowired
    CardRepository cardRepository;
    @Autowired
    PersonRepository personRepository;
    @Autowired
    PersonComponent personComponent;
    
    public void save(NoteModel noteModel) {
        Person personLoged = personRepository.findOne(noteModel.getId_writer());
        Note note = noteModel.convertToNote(noteModel, personLoged) ;
        Card card = cardRepository.findOne(noteModel.getId_card());
        card.getNotes().add(note);
        cardRepository.save(card);
    }
    
    public void update(NoteModel noteModel) {
       Person personLoged = personComponent.loggedPersonDetails();
        if(personLoged.getId() != noteModel.getId_writer()){
             throw new BadRequestException("Apenas o usuário que criou o comentario pode realizar esta ação.");
        }
        Note note = noteRepository.findOne(noteModel.getId());
        note.setText(noteModel.getText());
        noteRepository.save(note);
    }

    public void delete(Long id) {
        Person personLoged = personComponent.loggedPersonDetails();
        Note note = noteRepository.findOne(id);
        noteRepository.delete(id);
    }    
}
