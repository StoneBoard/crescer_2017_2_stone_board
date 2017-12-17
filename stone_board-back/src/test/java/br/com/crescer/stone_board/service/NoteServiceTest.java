/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.crescer.stone_board.service;


import br.com.crescer.stone_board.Utils.ConfigurationTest;
import br.com.crescer.stone_board.entity.Card;
import br.com.crescer.stone_board.entity.Note;
import br.com.crescer.stone_board.entity.model.NoteModel;
import br.com.crescer.stone_board.repository.CardRepository;
import br.com.crescer.stone_board.repository.NoteRepository;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Ignore;
import org.springframework.beans.factory.annotation.Autowired;


/**
 *
 * @author Marcele Dorneles
 */
public class NoteServiceTest extends ConfigurationTest 
{
    @Autowired
    NoteService noteService;

    @Autowired
    NoteRepository noteRepository;
    
    @Autowired
    CardRepository cardRepository;
    
    @Before
    public void setUp() {
        noteRepository.deleteAll();
    }
   
    @Test
   
    public void testSave(){
        Note note = DataGenerator.createNote();
        Card card = new Card();
        
        card.getNotes().add(note);
        cardRepository.save(card);
        
        Note noteResult = noteService.findById(new Long(1));
        
        assertEquals(note.getText(), noteResult.getText());
        assertEquals(note.getWriter(), noteResult.getWriter());
    }
    
    
    
    @Test
    public void testUpdate(){
        Note note = DataGenerator.createNote();
        Card card = new Card();
        
        card.getNotes().add(note);
        cardRepository.save(card);
       
        NoteModel noteModelTest = NoteModel.convertToNoteModel(noteService.findById(new Long(1)));
        noteModelTest.setText("Texto alterado");
       
        noteService.update(noteModelTest);
        
        Note noteResult = noteService.findById(new Long(1));
        
        assertNotEquals(note.getText(), noteResult.getText());
        assertEquals(note.getWriter(), noteResult.getWriter());
        assertEquals(noteModelTest.getText(), noteResult.getText());
        
    }
    

}
