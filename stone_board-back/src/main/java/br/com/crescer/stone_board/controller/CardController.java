/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.crescer.stone_board.controller;

import br.com.crescer.stone_board.entity.Board;
import br.com.crescer.stone_board.entity.Card;
import br.com.crescer.stone_board.entity.Person;
import br.com.crescer.stone_board.entity.model.BoardModel;
import br.com.crescer.stone_board.entity.model.CardModel;
import br.com.crescer.stone_board.service.CardService;
import br.com.crescer.stone_board.utils.PersonComponent;
import java.util.List;
import java.util.stream.Collectors;
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
 * @author Marcele Dorneles
 */
@RestController
@RequestMapping("/card")
public class CardController {

    @Autowired
    private CardService cardService;

    @Autowired
    private PersonComponent personComponent;

    @PostMapping
    public void Save(@Validated @RequestBody CardModel cardModel) {
        Person person = personComponent.loggedPersonDetails();
        cardService.save(cardModel, person);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity findById(@PathVariable Long id) {
        return ResponseEntity.ok(cardService.findById(id));
    }

    @GetMapping(path = "cardsOutsideResultGroup/{idBoard}")
    public List<CardModel> CardsOutsideResultGroup(@PathVariable("idBoard") Long idBoard) {
        List<Card> card = cardService.findAllCardsOutsideResultGroup(idBoard);
        return card.stream()
                .map(CardModel::convertToCardModel)
                .collect(Collectors.toList());
    }

    @PutMapping
    public void savePut(@RequestBody CardModel cardModel) {
        Person person = personComponent.loggedPersonDetails();
        cardService.update(cardModel, person.getId());
    }

    @DeleteMapping(path = "/{id}")
    public void delete(@PathVariable Long id) {
        Person person = personComponent.loggedPersonDetails();
        //  cardService.delete(id, person.getId());
    }
}
