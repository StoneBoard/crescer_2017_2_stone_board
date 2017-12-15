/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.crescer.stone_board.controller;

import br.com.crescer.stone_board.entity.model.ResultGroupRegisterModel;
import br.com.crescer.stone_board.service.ResultGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author marcele.dorneles
 */
@RestController
@RequestMapping("/resultGroup")
public class ResultGroupController {
    @Autowired
    private ResultGroupService resultGroupService;
    
    @PostMapping
    public void Save(@Validated @RequestBody ResultGroupRegisterModel resultGroupRegisterModel){
        resultGroupService.save(resultGroupRegisterModel);
    }
    
    @GetMapping(path = "/{id}")
    public ResponseEntity findById(@PathVariable Long id){
       return ResponseEntity.ok(resultGroupService.findById(id));
    }  
    
    @GetMapping("/findByBoard/{idBoard}")
    public ResponseEntity findByBoardId(@PathVariable Long idBoard){
       return ResponseEntity.ok(resultGroupService.findByBoardId(idBoard));
    }  
    
    @PutMapping
    public void update(@RequestBody ResultGroupRegisterModel resultGroupRegisterModel){
      resultGroupService.update(resultGroupRegisterModel);
    }
    
    @PutMapping("/addCards/{idResultGroup}/{idCard}")
    public void addCard(@PathVariable Long idResultGroup, @PathVariable  Long idCard){
         resultGroupService.addCard(idResultGroup, idCard);
    }
    
    @DeleteMapping(path = "/{idResultGroup}/{idBoard}")
    public void delete(@PathVariable Long idResultGroup, @PathVariable Long idBoard){
        resultGroupService.delete(idResultGroup, idBoard);
    }
}
