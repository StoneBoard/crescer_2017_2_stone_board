package br.com.crescer.stone_board.controller;

import br.com.crescer.stone_board.entity.Board;
import br.com.crescer.stone_board.entity.model.BoardModel;
import br.com.crescer.stone_board.service.BoardService;
import br.com.crescer.stone_board.webSocket.Greeting;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

/**
 *
 * @author willian
 */
@Controller
public class WebSocketBoardController {

    @Autowired
    BoardService boardService;

    @MessageMapping("/board/{id}")
    @SendTo("/stoneboard/sendBoard")
    public Greeting boardUpdate(@DestinationVariable Long id) throws Exception {
        Board board = boardService.findById(id);
        return new Greeting(BoardModel.convertToBoardModel(board));
    }

}
