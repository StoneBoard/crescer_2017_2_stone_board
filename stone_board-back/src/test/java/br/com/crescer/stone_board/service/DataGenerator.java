/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.crescer.stone_board.service;

import br.com.crescer.stone_board.entity.Board;
import br.com.crescer.stone_board.entity.BoardSession;
import br.com.crescer.stone_board.entity.Card;
import br.com.crescer.stone_board.entity.Person;
import br.com.crescer.stone_board.repository.BoardRepository;
import br.com.crescer.stone_board.repository.BoardSessionRepository;
import br.com.crescer.stone_board.repository.CardRepository;
import br.com.crescer.stone_board.repository.PersonRepository;
import java.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 *
 * @author Júlia
 */
public class DataGenerator {
    
    @Autowired
    private static PasswordEncoder encoder = new BCryptPasswordEncoder();
    
    @Autowired
    private static PersonRepository personRepository;
    @Autowired
    private static BoardRepository boardRepository;
    @Autowired
    private static BoardSessionRepository boardSessionRepository;
    @Autowired
    private static CardRepository cardRepository; 
    
    public static Person createPerson () {
        Person person = Person.builder()
                .fullName("Fulano da Silva")
                .email("fulano@email.com")
                .pass(encoder.encode("123456"))
                .build();
        
        personRepository.save(person);
        
        return person;
    }
    
    public static Board createBoard() {
        Board board = Board.builder()
                .title("Titulo do Board")
                .deadline(LocalDateTime.now())
                .build();
        
        Person person = createPerson();
        person.getMyBoards().add(board);
        
        return board; 
    }
    
    public static BoardSession createBoardSesssion() {
        BoardSession boardSession = BoardSession.builder()
                .title("Titulo da Session")
                .color(1)
                .icon("url.icon")
                .build();
        Board board = createBoard();
        board.getSessions().add(boardSession);
        
        return boardSession;
    }
    
    public static Card createCard() {
        Person person = createPerson();
        Card card = Card.builder()
                .text("Texto do meu post it")
                .writer(person)
                .creationDate(LocalDateTime.now())
                .build();
        
        BoardSession boardSession = createBoardSesssion();
        boardSession.getCards().add(card);
        
        return card;
    }
}
