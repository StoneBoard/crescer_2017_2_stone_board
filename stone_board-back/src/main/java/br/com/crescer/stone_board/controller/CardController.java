/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.crescer.stone_board.controller;

import br.com.crescer.stone_board.entity.Card;
import br.com.crescer.stone_board.entity.model.CardModel;
import br.com.crescer.stone_board.service.CardService;
import br.com.crescer.stone_board.webSocket.Greeting;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
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
 * @author Marcele Dorneles
 */
@RestController
@RequestMapping("/card")
public class CardController {
    @Autowired
    private CardService cardService;
    
    @PostMapping
    public void Save(@Validated @RequestBody CardModel cardModel){
        cardService.save(cardModel);
    }
    @GetMapping(path = "/{id}")
    public ResponseEntity FindById(@PathVariable Long id){
       return ResponseEntity.ok(cardService.findById(id));
    }  
    @PutMapping
    public void SavePut(@RequestBody CardModel cardModel){
      cardService.update(cardModel);
    }
    @DeleteMapping
    public void Delete(@RequestBody Long id){
        cardService.delete(id);
    }
    @MessageMapping("loadCardById/{id}")
    @SendTo("/card")
    public Greeting greeting(@PathVariable Long id) throws Exception {
        Thread.sleep(1000); 
         Card card = cardService.findById(id);
        return new Greeting(CardModel.convertToCardModel(card));
    }
}
