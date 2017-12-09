/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.crescer.stone_board.controller;

import br.com.crescer.stone_board.entity.model.BoardModel;
import br.com.crescer.stone_board.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Marcele Dorneles
 */
@RestController
@RequestMapping("/person")
public class PersonController {
    @Autowired
    private PersonService personService;
    
    @GetMapping("/listMyBoards")
    public ResponseEntity getMyBoardsByUserId() {
        return ResponseEntity.ok(personService.listMyBoards());
    }
    @GetMapping("/listConnectBoards")
    public ResponseEntity getConnectBoardsByUserId() {
        return ResponseEntity.ok(personService.listMyBoards());
    }
}
