package br.com.crescer.stone_board.controller;

import br.com.crescer.stone_board.utils.PersonComponent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author willian
 */
@RestController
@RequestMapping(path = "/initial", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class LoginController {

    @Autowired
    PersonComponent personComponent;

    @GetMapping(path = "/login")
    public ResponseEntity getLoggedPerson() {
        return ResponseEntity.ok(personComponent.loggedPerson());
    }
}
