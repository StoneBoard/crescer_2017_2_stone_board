/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.crescer.stone_board.service;

import br.com.crescer.stone_board.entity.Card;
import br.com.crescer.stone_board.entity.Vote;
import br.com.crescer.stone_board.entity.Person;
import br.com.crescer.stone_board.entity.model.VoteModel;
import br.com.crescer.stone_board.repository.CardRepository;
import br.com.crescer.stone_board.repository.VoteRepository;
import br.com.crescer.stone_board.utils.PersonComponent;
import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author marcele.dorneles
 */
@Service
public class VoteService {
    
    @Autowired
    VoteRepository voteRepository;
    @Autowired
    CardRepository cardRepository;
    @Autowired
    PersonComponent personComponent;
    
    public void save(VoteModel voteModel){
        Person personLoged = personComponent.loggedPersonDetails();
        Card card = cardRepository.findOne(voteModel.getId_card());
        
        Vote vote = card.getVotes().stream()
                                .filter(v ->  Objects.equals(v.getPerson().getId(), personLoged.getId()))
                                .findFirst()
                                .get();
        if(vote != null){
            vote.setPositive(voteModel.isPositive());
        }
        else{
            card.getVotes().add(new Vote ( null,personLoged, voteModel.isPositive()));
        }
        cardRepository.save(card);
    }
    
}
