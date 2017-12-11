/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.crescer.stone_board.controller;

import br.com.crescer.stone_board.entity.Board;
import br.com.crescer.stone_board.entity.model.BoardModel;
import br.com.crescer.stone_board.service.PersonService;
import br.com.crescer.stone_board.webSocket.Greeting;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
        List<Board> boards = personService.listMyBoards();
        List<BoardModel> boardsModel = boards.stream()
                .map(BoardModel::convertToBoardModel)
                .collect(Collectors.toList());
    
        //BoardModel newBoard = new BoardModel("xuxuxu", LocalDateTime.now(), true, null, null);
    
        return ResponseEntity.ok(boardsModel);
    }
    @GetMapping("/listConnectBoards")
    public List<BoardModel> getConnectBoardsByUserId() {
        List<Board> boards = personService.listConnectBoards();
        return boards.stream()
                .map(BoardModel::convertToBoardModel)
                .collect(Collectors.toList());
    }
    
    @MessageMapping("/loadMyBoardsByIdPerson")
    @SendTo("/api/sendMyBoardsByIdPerson")
    public Greeting myBoardsgreeting() throws Exception {
        Thread.sleep(1000); 
//        List<Board> boards = personService.listMyBoards();
//        List<BoardModel> boardsModel = boards.stream()
//                .map(BoardModel::convertToBoardModel)
//                .collect(Collectors.toList());
    
        return new Greeting("teste");
    }
    
    
    @MessageMapping("/loadConectedBoardByIdPerson")
    @SendTo("/api/sendConectedBoardByIdPerson")
    public Greeting connectBoardsGretting(@PathVariable Long id) throws Exception {
        Thread.sleep(1000); 
        List<Board> boards = personService.listConnectBoards();
        return new Greeting(boards.stream()
                .map(BoardModel::convertToBoardModel)
                .collect(Collectors.toList()));
    }
}
