package br.com.crescer.stone_board.controller;

import br.com.crescer.stone_board.entity.Board;
import br.com.crescer.stone_board.entity.Person;
import br.com.crescer.stone_board.entity.model.BoardModel;
import br.com.crescer.stone_board.entity.model.CardModel;
import br.com.crescer.stone_board.entity.model.NoteModel;
import br.com.crescer.stone_board.entity.model.VoteModel;
import br.com.crescer.stone_board.service.BoardService;
import br.com.crescer.stone_board.service.CardService;
import br.com.crescer.stone_board.service.NoteService;
import br.com.crescer.stone_board.service.PersonService;
import br.com.crescer.stone_board.service.VoteService;
import br.com.crescer.stone_board.utils.PersonComponent;
import br.com.crescer.stone_board.webSocket.Greeting;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;

/**
 *
 * @author willian.pazinatto
 */
@Controller
public class WebSocketController {
    
    @Autowired
    PersonService personService;
    
    @Autowired
    PersonComponent personComponent;
    
    @Autowired
    BoardService boardService;
    
    @Autowired
    CardService cardService;
    
    @Autowired
    VoteService voteService;
    
    @Autowired
    NoteService noteService;
    
    @MessageMapping("/person")
    @SendTo("/stoneboard/sendPerson")
    public Person loggedPerson() throws InterruptedException {
        Person person = personComponent.loggedPersonDetails();
        return person;
    }
    
    @MessageMapping("/board/{id}")
    @SendTo("/stoneboard/sendBoard") 
    public Greeting boardUpdate(@DestinationVariable Long id) throws Exception {
        Board board = boardService.findById(id);
        return new Greeting(BoardModel.convertToBoardModel(board));
    }
    
    @MessageMapping("/card/new/{idBoard}")
    @SendTo("/stoneboard/sendBoard")
    public Greeting saveNewCard(
            @DestinationVariable Long idBoard, 
            @Validated CardModel cardModel) throws Exception {
        
        Person person = personComponent.loggedPersonDetails();
        cardService.save(cardModel, person);
        return new Greeting(findBoardModel(idBoard));
    }
    
    @MessageMapping("/card/edit/{idBoard}")
    @SendTo("/stoneboard/sendBoard")
    public Greeting editCard(
            @DestinationVariable Long idBoard,
            @Validated CardModel cardModel) throws Exception {
        
        Person person = personComponent.loggedPersonDetails();
        cardService.update(cardModel, person.getId());
        return new Greeting(findBoardModel(idBoard));
    }
    
    @MessageMapping("/card/delete/{idBoard}")
    @SendTo("/stoneboard/sendBoard")
    public Greeting deleteCard(
            @DestinationVariable Long idBoard, 
            CardModel cardModel) throws Exception {
        Person person = personComponent.loggedPersonDetails();
        cardService.delete(cardModel.getId(), cardModel.getId_session(), person.getId());
        return new Greeting(findBoardModel(idBoard));
    }
    
    @MessageMapping("/vote/{idBoard}")
    @SendTo("/stoneboard/sendBoard")
    public Greeting vote(
            @DestinationVariable Long idBoard, 
            VoteModel voteModel) throws Exception {
        
        Person person = personComponent.loggedPersonDetails();
        voteService.save(voteModel, person);
        return new Greeting(findBoardModel(idBoard));
    }
    
    @MessageMapping("/note/new/{idCard}")
    @SendTo("/stoneboard/sendComment")
    public Greeting note(
            @DestinationVariable Long idCard, 
            @Validated NoteModel noteModel) throws Exception {
        Person person = personComponent.loggedPersonDetails();
        noteService.save(noteModel, person);
        return new Greeting(findCardModel(idCard));
    }
    
    @MessageMapping("/note/delete/{idCard}")
    @SendTo("/stoneboard/sendComment")
    public Greeting deleteNote(
            @DestinationVariable Long idCard, 
            NoteModel noteModel) throws Exception {
        Person person = personComponent.loggedPersonDetails();
        noteService.delete(noteModel.getId());
        return new Greeting(findCardModel(idCard));
    }
    
    
    private BoardModel findBoardModel(Long id) {
        return BoardModel.convertToBoardModel(boardService.findById(id));
    }
    
    private CardModel findCardModel(Long id) {
        return CardModel.convertToCardModel(cardService.findById(id));
    }
}
