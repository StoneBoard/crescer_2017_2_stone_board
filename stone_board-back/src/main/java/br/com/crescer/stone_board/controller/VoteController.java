package br.com.crescer.stone_board.controller;

import br.com.crescer.stone_board.entity.model.VoteModel;
import br.com.crescer.stone_board.service.VoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author marcele.dorneles
 */
@RestController
@RequestMapping("/vote")
public class VoteController {
    @Autowired
    private VoteService voteService;
    
    @PostMapping
    public void Save(@Validated @RequestBody VoteModel voteModel){
       voteService.save(voteModel);
    }
}
