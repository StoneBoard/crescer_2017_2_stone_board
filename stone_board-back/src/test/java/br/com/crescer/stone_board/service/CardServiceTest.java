/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.crescer.stone_board.service;

import br.com.crescer.stone_board.Utils.ConfigurationTest;
import br.com.crescer.stone_board.entity.Card;
import br.com.crescer.stone_board.entity.Person;
import br.com.crescer.stone_board.repository.CardRepository;
import br.com.crescer.stone_board.repository.PersonRepository;
import java.time.LocalDateTime;
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
    PersonRepository personRepository;

    @Before
    public void setUp() {
        cardRepository.deleteAll();
    }

    @Test
    public void testSave() {
        Person person = personRepository.save(getPersonOne());
        Card card = cardRepository.save(getCardOne(person));
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
  
     private Person getPersonOne() {
        return Person.builder()
                .fullName("Willian Teste")
                .email("willian@teste.com")
                .pass("teste")
                .build();
    }

    
    private Card getCardOne(Person person) {
        return Card.builder()
               .id(new Long(1))
               .text("Meu card")
               .writer(person)
               .creationDate(LocalDateTime.now())
               .build();
       
    }
    
}

