package br.com.crescer.stone_board.controller;

import br.com.crescer.stone_board.entity.Board;
import br.com.crescer.stone_board.entity.Person;
import br.com.crescer.stone_board.entity.model.BoardMemberModel;
import br.com.crescer.stone_board.entity.model.BoardModel;
import br.com.crescer.stone_board.entity.model.BoardRegisterModel;
import br.com.crescer.stone_board.service.BoardService;
import br.com.crescer.stone_board.utils.BadRequestException;
import br.com.crescer.stone_board.utils.NotFoundException;
import br.com.crescer.stone_board.utils.PersonComponent;
import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping(path = "/board")
public class BoardController {

    @Autowired
    private BoardService boardService;
    
    @Autowired
    PersonComponent personComponent;

    @PostMapping
    public void Save(@Validated @RequestBody BoardRegisterModel boardRegister) {
        Person person = personComponent.loggedPersonDetails();
        boardService.save(boardRegister, person);
    }

    @PutMapping(path = "/addMember")
    public void addMembers(@RequestBody BoardMemberModel boardMemberModel) {
        Person person = personComponent.loggedPersonDetails();
        boardService.addMembers(boardMemberModel,person);
    }

    @PutMapping
    public BoardModel update(@Validated @RequestBody BoardRegisterModel boardRegister) {
        return boardService.update(boardRegister);
    }

    @GetMapping("/{id}")
    public BoardModel findById(@PathVariable Long id) throws Exception {
        Board board = boardService.findById(id);
        Person person = personComponent.loggedPersonDetails();
        
        if(board == null) {
            throw new NotFoundException("Board não Encontrado");
        }
        
        if (boardService.userAuthenticadedBoard(id, person)) {
            return BoardModel.convertToBoardModel(board);
        } else {
            throw new BadRequestException("Não Autorizado");
        }

    }

}
