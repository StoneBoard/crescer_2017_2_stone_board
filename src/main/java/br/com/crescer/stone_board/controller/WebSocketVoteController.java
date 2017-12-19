package br.com.crescer.stone_board.controller;

import br.com.crescer.stone_board.entity.Person;
import br.com.crescer.stone_board.entity.model.BoardModel;
import br.com.crescer.stone_board.entity.model.VoteModel;
import br.com.crescer.stone_board.service.BoardService;
import br.com.crescer.stone_board.service.VoteService;
import br.com.crescer.stone_board.utils.PersonComponent;
import br.com.crescer.stone_board.webSocket.Greeting;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

/**
 *
 * @author willian.pazinatto
 */
@Controller
public class WebSocketVoteController {

    @Autowired
    PersonComponent personComponent;

    @Autowired
    BoardService boardService;

    @Autowired
    VoteService voteService;

    @MessageMapping("/vote/{idBoard}")
    @SendTo("/stoneboard/sendBoard")
    public Greeting vote(
            @DestinationVariable Long idBoard,
            VoteModel voteModel) throws Exception {

        Person person = personComponent.loggedPersonDetails();
        voteService.save(voteModel, person);
        return new Greeting(findBoardModel(idBoard));
    }

    private BoardModel findBoardModel(Long id) {
        return BoardModel.convertToBoardModel(boardService.findById(id));
    }

}
