package br.com.crescer.stone_board.controller;

import br.com.crescer.stone_board.entity.Person;
import br.com.crescer.stone_board.utils.PersonComponent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

/**
 *
 * @author willian
 */
@Controller
public class WebSocketPersonController {

    @Autowired
    PersonComponent personComponent;

    @MessageMapping("/person")
    @SendTo("/stoneboard/sendPerson")
    public Person loggedPerson() throws InterruptedException {
        Person person = personComponent.loggedPersonDetails();
        return person;
    }

}
