/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.crescer.stone_board.service;

import br.com.crescer.stone_board.Utils.ConfigurationTest;
import br.com.crescer.stone_board.entity.Board;
import br.com.crescer.stone_board.entity.BoardSession;
import br.com.crescer.stone_board.entity.Card;
import br.com.crescer.stone_board.entity.Note;
import br.com.crescer.stone_board.entity.Person;
import br.com.crescer.stone_board.entity.model.CardModel;
import br.com.crescer.stone_board.entity.model.NoteModel;
import br.com.crescer.stone_board.repository.BoardRepository;
import br.com.crescer.stone_board.repository.BoardSessionRepository;
import br.com.crescer.stone_board.repository.CardRepository;
import br.com.crescer.stone_board.repository.NoteRepository;
import br.com.crescer.stone_board.repository.PersonRepository;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Ignore;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author Marcele Dorneles
 */
public class NoteServiceTest extends ConfigurationTest {

    @Autowired
    NoteService noteService;
    
    @Autowired
    NoteRepository noteRepository;
    
    @Autowired
    CardRepository cardRepository;
    
    @Autowired
    CardService cardService;
    
    @Autowired
    BoardSessionRepository boardSessionRepository;
    
    @Autowired
    PersonRepository personRepository;
    
    @Autowired
    BoardRepository boardRepository;

    @Before
    public void setUp() {
        noteRepository.deleteAll();
        cardRepository.deleteAll();
    }

    @Test
    public void testFindByIdWithNotExists() {
        assertNull(noteService.findById(200l));
    }

    private Card newCard() {
        Person person = DataGenerator.createPerson();
        personRepository.save(person);

        Board board = DataGenerator.createBoard();
        person.getMyBoards().add(board);
        personRepository.save(person);

        BoardSession boardSession = DataGenerator.createBoardSesssion();
        board.getSessions().add(boardSession);
        boardRepository.save(board);

        Card card = DataGenerator.createCard();
        boardSession.getCards().add(card);
        boardSessionRepository.save(boardSession);

        return card;
    }

}
