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
import br.com.crescer.stone_board.entity.Person;
import br.com.crescer.stone_board.repository.BoardSessionRepository;
import br.com.crescer.stone_board.repository.CardRepository;
import br.com.crescer.stone_board.repository.PersonRepository;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Ignore;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author willian
 */
public class CardServiceTest extends ConfigurationTest {

    @Autowired
    CardService cardService;

    @Autowired
    CardRepository cardRepository;
    @Autowired
    BoardSessionRepository boardSessionRepository;
    @Autowired
    PersonRepository personRepository;
    

    @Before
    public void setUp() {
        cardRepository.deleteAll();
    }

    @Test
    @Ignore
    public void testSave() {
        Card card = DataGenerator.createCard();
        BoardSession boardSession = newBoardSession();
        boardSession.getCards().add(card);
        boardSessionRepository.save(boardSession);
        
        Card result = cardService.findById(new Long(1));

        assertEquals(card.getWriter(), result.getWriter());
        assertEquals(card.getText(), result.getText());
        assertEquals(card.getCreationDate(), result.getCreationDate());
    }
    ;
    @Test
    public void testFindByIdWithNotExists() {
        assertNull(cardService.findById(200l));
    }
    
    private BoardSession newBoardSession() {
        Person person = DataGenerator.createPerson();
        Board board = DataGenerator.createBoard();
        BoardSession boardSession = DataGenerator.createBoardSesssion();
        board.getSessions().add(boardSession);
        
        personRepository.save(person);
        
        return boardSession;
    }
 
}

