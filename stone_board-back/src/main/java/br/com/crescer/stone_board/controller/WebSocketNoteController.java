package br.com.crescer.stone_board.controller;

import br.com.crescer.stone_board.entity.Person;
import br.com.crescer.stone_board.entity.model.CardModel;
import br.com.crescer.stone_board.entity.model.NoteModel;
import br.com.crescer.stone_board.service.BoardService;
import br.com.crescer.stone_board.service.CardService;
import br.com.crescer.stone_board.service.NoteService;
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
 * @author willian
 */
@Controller
public class WebSocketNoteController {

    @Autowired
    PersonComponent personComponent;

    @Autowired
    BoardService boardService;

    @Autowired
    CardService cardService;

    @Autowired
    NoteService noteService;

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
        noteService.delete(noteModel.getId());
        return new Greeting(findCardModel(idCard));
    }

    private CardModel findCardModel(Long id) {
        return CardModel.convertToCardModel(cardService.findById(id));
    }
}
