package br.com.crescer.stone_board.controller;

import br.com.crescer.stone_board.entity.Person;
import br.com.crescer.stone_board.entity.model.BoardModel;
import br.com.crescer.stone_board.entity.model.CardModel;
import br.com.crescer.stone_board.service.BoardService;
import br.com.crescer.stone_board.service.CardService;
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
public class WebSocketCardController {

    @Autowired
    PersonComponent personComponent;

    @Autowired
    BoardService boardService;

    @Autowired
    CardService cardService;

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

    private BoardModel findBoardModel(Long id) {
        return BoardModel.convertToBoardModel(boardService.findById(id));
    }
}
