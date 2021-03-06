package br.com.crescer.stone_board.controller;

import br.com.crescer.stone_board.entity.Board;
import br.com.crescer.stone_board.entity.Person;
import br.com.crescer.stone_board.entity.model.BoardModel;
import br.com.crescer.stone_board.entity.model.PersonModel;
import br.com.crescer.stone_board.service.PersonService;
import br.com.crescer.stone_board.utils.PersonComponent;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Marcele Dorneles
 */
@RestController
@RequestMapping(path = "/person")
public class PersonController {

    @Autowired
    private PersonService personService;
    @Autowired
    private PersonComponent personComponent;

    @PostMapping(path = "/addPerson")
    public void addPerson(@Validated @RequestBody PersonModel personModel) {
        personService.createAccount(personModel);
    }

    @GetMapping(path = "/listMyBoards")
    public ResponseEntity getMyBoardsByUserId() {
        Person person = personComponent.loggedPersonDetails();
        List<Board> boards = personService.listMyBoards(person);
        List<BoardModel> boardsModel = boards.stream()
                .map(BoardModel::convertToBoardModel)
                .collect(Collectors.toList());

        return ResponseEntity.ok(boardsModel);
    }

    @GetMapping(path = "/listConnectBoards")
    public List<BoardModel> getConnectBoardsByUserId() {
        Person person = personComponent.loggedPersonDetails();
        List<Board> boards = personService.listConnectBoards(person);
        return boards.stream()
                .map(BoardModel::convertToBoardModel)
                .collect(Collectors.toList());
    }

    @GetMapping(path = "/{email}")
    public List<PersonModel> findByEmail(@PathVariable String email) {
        return personService.findPersonsByEmail(email);
    }

    @GetMapping(path = "/isAdmin/{id}")
    public boolean isAdmin(@PathVariable Long id) {
        Person person = personComponent.loggedPersonDetails();
        List<Board> boards = person.getMyBoards();
        return boards.stream().anyMatch(b -> b.getId() == id);
    }
}
