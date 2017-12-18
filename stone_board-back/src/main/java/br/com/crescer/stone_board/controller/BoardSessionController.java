package br.com.crescer.stone_board.controller;

import br.com.crescer.stone_board.entity.BoardSession;
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
@RequestMapping(path = "/session")
public class BoardSessionController {

    @Autowired
    private BoardSessionService boardSessionService;

    @PostMapping
    public void Save(@Validated @RequestBody BoardSessionModel boardSessionModel) {
        boardSessionService.save(boardSessionModel);
    }

    @GetMapping(path = "/findById/{id}")
    public BoardSessionModel findById(Long id) {
        BoardSession boardSession = boardSessionService.findById(id);
        return BoardSessionModel.convertToBoardSessionModel(boardSession);
    }

    @GetMapping(path = "/findAll")
    public ResponseEntity findAll() {
        return ResponseEntity.ok(boardSessionService.findAllBoards());
    }

}
