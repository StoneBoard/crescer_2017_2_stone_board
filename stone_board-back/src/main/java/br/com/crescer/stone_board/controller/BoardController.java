package br.com.crescer.stone_board.controller;

import br.com.crescer.stone_board.webSocket.Greeting;
import br.com.crescer.stone_board.entity.Board;
import br.com.crescer.stone_board.entity.model.BoardMemberModel;
import br.com.crescer.stone_board.entity.model.BoardModel;
import br.com.crescer.stone_board.entity.model.PersonModel;
import br.com.crescer.stone_board.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Marcele Dorneles
 */
@RestController
@RequestMapping("/board")
public class BoardController {
    
    @Autowired
    private BoardService boardService;
    
    @PostMapping
    public ResponseEntity Save(@Validated @RequestBody BoardModel boardModel){
       return ResponseEntity.ok(boardService.save(boardModel));
    }
    @PutMapping("/addMember")
    public PersonModel addMembers(@RequestBody BoardMemberModel boardMemberModel){
        return boardService.addMembers(boardMemberModel);
    }
    @PutMapping
    public BoardModel update(BoardModel boardModel){
        return boardService.update(boardModel);
    }
    
    @GetMapping("/{id}")
    public BoardModel FindById(@PathVariable Long id){
        Board board = boardService.findById(id);
        return BoardModel.convertToBoardModel(board);
    }       
    
    @MessageMapping("loadBoardById/{id}")
    @SendTo("/")
    public Greeting greeting(@PathVariable Long id) throws Exception {
        Thread.sleep(1000); 
        Board board = boardService.findById(id);
        return new Greeting(BoardModel.convertToBoardModel(board));
    }
}
