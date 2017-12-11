/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.crescer.stone_board.controller;

import br.com.crescer.stone_board.entity.Board;
import br.com.crescer.stone_board.entity.BoardSession;
import br.com.crescer.stone_board.entity.model.BoardModel;
import br.com.crescer.stone_board.entity.model.BoardSessionModel;
import br.com.crescer.stone_board.service.BoardSessionService;
import br.com.crescer.stone_board.webSocket.Greeting;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
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
@RequestMapping("/session")
public class BoardSessionController {
    
    @Autowired
    private BoardSessionService boardSessionService;
    
    @PostMapping
    public ResponseEntity Save(@Validated @RequestBody BoardSessionModel boardSessionModel){
        return ResponseEntity.ok(boardSessionService.save(boardSessionModel));
    }
    @GetMapping(path = "/findById/{id}")
    public BoardSessionModel FindById(Long id){
        BoardSession boardSession = boardSessionService.findById(id);
        return BoardSessionModel.convertToBoardSessionModel(boardSession);
    }
    
    @GetMapping(path = "/findAll")
    public ResponseEntity FindAll(){
       return ResponseEntity.ok(boardSessionService.findAllBoards());
    }
    
    @MessageMapping("loadSessionById/{id}")
    @SendTo("/session")
    public Greeting greeting(@PathVariable Long id) throws Exception {
        Thread.sleep(1000); 
         BoardSession boardSession = boardSessionService.findById(id);
        return new Greeting(BoardSessionModel.convertToBoardSessionModel(boardSession));
    }
}
