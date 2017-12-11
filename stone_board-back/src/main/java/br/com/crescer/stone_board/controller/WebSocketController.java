package br.com.crescer.stone_board.controller;

import br.com.crescer.stone_board.entity.Person;
import br.com.crescer.stone_board.service.PersonService;
import br.com.crescer.stone_board.utils.PersonComponent;
import java.util.ArrayList;
import java.util.List;
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

    @MessageMapping("/person")
    @SendTo("/stoneboard/sendPerson")
    public List<Person> loggedPerson() throws InterruptedException {
        List<Person> retorno = new ArrayList<>();
        retorno.add(personComponent.loggedPersonDetails());

        return retorno;
    }

}
