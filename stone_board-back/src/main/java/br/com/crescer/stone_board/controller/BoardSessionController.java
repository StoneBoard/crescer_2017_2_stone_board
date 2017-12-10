/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.crescer.stone_board.controller;

import br.com.crescer.stone_board.entity.model.BoardSessionModel;
import br.com.crescer.stone_board.service.BoardSessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Marcele Dorneles
 */
@RestController
@RequestMapping("/session")
public class BoardSessionController {
    
    @Autowired
    private BoardSessionService boardSessionService;
    
    @PostMapping
    public ResponseEntity Save(@Validated @RequestBody BoardSessionModel boardSessionModel){
        return ResponseEntity.ok(boardSessionService.save(boardSessionModel));
    }
    @GetMapping(path = "/findById/{id}")
    public ResponseEntity FindById(Long id){
       return ResponseEntity.ok(boardSessionService.findById(id));
    }
    
    @GetMapping(path = "/findAll")
    public ResponseEntity FindAll(){
       return ResponseEntity.ok(boardSessionService.findAllBoards());
    }
}
