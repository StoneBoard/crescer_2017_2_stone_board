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
import br.com.crescer.stone_board.entity.model.BoardRegisterModel;
import br.com.crescer.stone_board.entity.model.BoardSessionModel;
import br.com.crescer.stone_board.entity.model.BoardSessionRegisterModel;
import br.com.crescer.stone_board.entity.model.CardModel;
import br.com.crescer.stone_board.repository.PersonRepository;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
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
    
    public static Board createBoard() {
        Board board = Board.builder()
                .title("Titulo do Board")
                .deadline(LocalDateTime.now())
                .build();
               
        return board; 
    }
    
    public static BoardRegisterModel createBoardRegisterModel() {
        List<BoardSessionRegisterModel> sessions = null;
        sessions.add(createBoardSesssionRegisterModel());
        BoardRegisterModel boardRegisterModel = BoardRegisterModel.builder()
                .title("Titulo do Board")
                .deadline(LocalDateTime.now())
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
