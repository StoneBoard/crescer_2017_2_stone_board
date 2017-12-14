/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.crescer.stone_board.controller;

import br.com.crescer.stone_board.entity.Board;
import br.com.crescer.stone_board.entity.model.BoardModel;
import br.com.crescer.stone_board.entity.model.PersonModel;
import br.com.crescer.stone_board.service.PersonService;
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
@RequestMapping("/person")
public class PersonController {
    @Autowired
    private PersonService personService;
    
    @PostMapping("/addPerson")
    public ResponseEntity addPerson(@Validated @RequestBody PersonModel personModel) {
        return ResponseEntity.ok(personService.createAcount(personModel));
    }
        
    @GetMapping("/listMyBoards")
    public ResponseEntity getMyBoardsByUserId() {
        List<Board> boards = personService.listMyBoards();
        List<BoardModel> boardsModel = boards.stream()
                .map(BoardModel::convertToBoardModel)
                .collect(Collectors.toList());

        return ResponseEntity.ok(boardsModel);
    }
    @GetMapping("/listConnectBoards")
    public List<BoardModel> getConnectBoardsByUserId() {
        List<Board> boards = personService.listConnectBoards();
        return boards.stream()
                .map(BoardModel::convertToBoardModel)
                .collect(Collectors.toList());
    }
    @GetMapping("/{email}")
    public List<PersonModel> findByEmail(@PathVariable String email){
        return personService.findPersonsByEmail(email);
    }
}
