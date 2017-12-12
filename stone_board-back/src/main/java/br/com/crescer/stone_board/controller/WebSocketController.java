package br.com.crescer.stone_board.controller;

import br.com.crescer.stone_board.entity.Board;
import br.com.crescer.stone_board.entity.Notification;
import br.com.crescer.stone_board.entity.Person;
import br.com.crescer.stone_board.service.BoardService;
import br.com.crescer.stone_board.service.PersonService;
import br.com.crescer.stone_board.utils.PersonComponent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

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

//    @Autowired
//    PersonComponent personComponent;
    
    @Autowired
    BoardService boardService;

    @MessageMapping("/person")
    @SendTo("/stoneboard/sendPerson")
    public Person loggedPerson() throws InterruptedException {
        Person person = personComponent.loggedPersonDetails();
        return person;
    }

    @MessageMapping("/board/{id}")
    @SendTo("/stoneboard/sendBoard")
    public Board boardUpdate(Long id) throws InterruptedException {
        return boardService.findById(id);
   }
    
//        @MessageMapping("/notification/{id}")
//    @SendTo("/stoneboard/sendBoard")
//    public Notification notificationUpdate(Long id) throws InterruptedException {
//        return boardService.findById(id);
//   }

}
