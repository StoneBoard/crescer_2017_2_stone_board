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
import br.com.crescer.stone_board.entity.model.BoardRegisterModel;
import br.com.crescer.stone_board.entity.model.BoardSessionModel;
import br.com.crescer.stone_board.entity.model.CardModel;
import br.com.crescer.stone_board.repository.BoardRepository;
import br.com.crescer.stone_board.repository.BoardSessionRepository;
import br.com.crescer.stone_board.repository.CardRepository;
import br.com.crescer.stone_board.repository.PersonRepository;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
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
    BoardSessionService boardSessionService;
    @Autowired
    PersonRepository personRepository;
    @Autowired
    BoardService boardService;
    
     
    @Before
    public void setUp() {
        cardRepository.deleteAll();
    }

    @Test
    public void testSave() {
        Person person = DataGenerator.createPerson();
        personRepository.save(person);
        Person personResult = personRepository.findOne(new Long(1));
        
        
          
        CardModel cardModel = DataGenerator.createCardModel();
        cardService.save(cardModel, personResult);
        
        Card result = cardService.findById(new Long(1));

        assertEquals(cardModel.getId_writer(), result.getWriter().getId());
        assertEquals(cardModel.getText(), result.getText());
        assertEquals(cardModel.getCreationDate(), result.getCreationDate());
    }
    
    @Test
    public void testUpdate(){
        Person person = DataGenerator.createPerson();
        personRepository.save(person);
        Person personResult = personRepository.findOne(new Long(1));
        
        CardModel cardModel = DataGenerator.createCardModel();
        cardService.save(cardModel, personResult);
       
        CardModel cardModelTest = CardModel.convertToCardModel(cardService.findById(new Long(1)));
        
        cardModelTest.setText("Texto testando a edição");
        cardService.update(cardModelTest, new Long(1));
        
        Card cardResult = cardService.findById(new Long(1));
        
        assertEquals(cardModel.getId_writer(), result.getWriter().getId());
        assertNotEquals(cardModel.getText(), cardResult.getText());
        assertEquals(cardModel.getCreationDate(), cardResult.getCreationDate());
        
    }
    
    @Test
    public void testFindByIdWithNotExists() {
        assertNull(cardService.findById(200l));
    }
    
    private BoardSession newBoardSession() {
          BoardSessionModel boardSessionModel = DataGenerator.createBoardSesssionModel();
          boardSessionService.save(boardSessionModel);
          
          BoardSession boardSession = boardSessionService.findById(new Long(1));
        
        return boardSession;
    }

}

