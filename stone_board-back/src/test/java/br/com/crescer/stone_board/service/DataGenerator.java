/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.crescer.stone_board.service;

import br.com.crescer.stone_board.entity.Board;
import br.com.crescer.stone_board.entity.BoardSession;
import br.com.crescer.stone_board.entity.Card;
import br.com.crescer.stone_board.entity.Note;
import br.com.crescer.stone_board.entity.Person;
import br.com.crescer.stone_board.entity.model.BoardModel;
import br.com.crescer.stone_board.entity.model.BoardRegisterModel;
import br.com.crescer.stone_board.entity.model.BoardSessionModel;
import br.com.crescer.stone_board.entity.model.BoardSessionRegisterModel;
import br.com.crescer.stone_board.entity.model.CardModel;
import br.com.crescer.stone_board.entity.model.PersonModel;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 *
 * @author Júlia
 */
public class DataGenerator {
    
    private static PasswordEncoder encoder = new BCryptPasswordEncoder();
    
    public static Person createPerson () {        
        
        Person person = Person.builder()
                .fullName("Fulano da Silva")
                .email("fulano@email.com")
                .pass(encoder.encode("123456"))
                .build();
                
        return person;
    }
    
    public static PersonModel createPersonModel () {        
        
        PersonModel personModel = PersonModel.builder()
                .fullName("Fulaninho da Silveira")
                .email("fulaniho@email.com")
                .pass(encoder.encode("123456"))
                .build();
                
        return personModel;
    }
    
    public static Board createBoard() {
         List<BoardSession> sessions = new ArrayList<>();
        sessions.add(createBoardSesssion());
         List<Person> members = new ArrayList<>();
        members.add(createPerson());
        Board board = Board.builder()
                .title("Titulo do Board")
                .deadline(LocalDateTime.now().plusDays(1L))
                .members(members)
                .sessions(sessions)
                .build();
               
        return board; 
    }
    
    public static BoardModel createBoardModel() {
        List<BoardSessionModel> sessions = new ArrayList<>();
        sessions.add(createBoardSesssionModel());
         List<PersonModel> members = new ArrayList<>();
        members.add(createPersonModel());
        BoardModel boardModel = BoardModel.builder()
                .title("Titulo do Board")
                .deadline(LocalDateTime.now().plusDays(1))
                .members(members)
                .sessions(sessions)
                .build();
               
        return boardModel; 
    }
    
    
    public static BoardRegisterModel createBoardRegisterModel() {
        List<BoardSessionRegisterModel> sessions = new ArrayList<>();
        sessions.add(createBoardSesssionRegisterModel());
        BoardRegisterModel boardRegisterModel = BoardRegisterModel.builder()
                .title("Titulo do Board")
                .deadline(LocalDateTime.now().plusDays(1))
                .sessions(sessions)
                .build();
               
        return boardRegisterModel; 
    }
    
    public static BoardSession createBoardSesssion() {
        BoardSession boardSession = BoardSession.builder()
                .title("Titulo da Session")
                .color(1)
                .icon("url.icon")
                .build();
        return boardSession;
    }
    
    public static BoardSessionModel createBoardSesssionModel() {
        BoardSessionModel boardSessionModel = BoardSessionModel.builder()
                .title("Titulo da Session")
                .color(1)
                .icon("url.icon")
                .build();
        return boardSessionModel;
    }
    
     public static BoardSessionRegisterModel createBoardSesssionRegisterModel() {
        BoardSessionRegisterModel boardSessionRegisterModel = BoardSessionRegisterModel.builder()
                .title("Titulo da Session")
                .color(1)
                .icon("url.icon")
                .build();
        return boardSessionRegisterModel;
    }
    
    public static Card createCard() {
        Person person = createPerson();
        Card card = Card.builder()
                .text("Texto do meu post it")
                .writer(person)
                .creationDate(LocalDateTime.now())
                .build();
                
        return card;
    }
    public static CardModel createCardModel() {
        Person person = createPerson();
        CardModel cardModel = CardModel.builder()
                .text("Texto do meu post it")
                .id_writer(new Long(1))
                .creationDate(LocalDateTime.now())
                .color(1)
                .id_session(new Long(1))
                .build();
                
        return cardModel;
    }
    
    public static Note createNote(){
        Person person = createPerson();
        Note note = Note.builder()
                .writer(person)
                .text("Texto do meu comentário")
                .build();
        return note;
    }
}
