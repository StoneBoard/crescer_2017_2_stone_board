/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.crescer.stone_board.service;

import br.com.crescer.stone_board.entity.Note;
import br.com.crescer.stone_board.entity.Person;
import br.com.crescer.stone_board.entity.model.NoteModel;
import br.com.crescer.stone_board.repository.CardRepository;
import br.com.crescer.stone_board.repository.NoteRepository;
import br.com.crescer.stone_board.repository.PersonRepository;
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
    
    public void save(NoteModel noteModel) {
        Person personLoged = personRepository.findOne(noteModel.getId_writer());
        Note note = noteModel.convertToNote(noteModel, personLoged) ;
        noteRepository.save(note);
    }
    
    public void update(NoteModel noteModel) {
        Note note = noteRepository.findOne(noteModel.getId());
        note.setText(noteModel.getText());
        noteRepository.save(note);
    }

    public void delete(Long id) {
        noteRepository.delete(id);
    }
    
}
