package br.com.crescer.stone_board.service;

import br.com.crescer.stone_board.entity.Card;
import br.com.crescer.stone_board.entity.Vote;
import br.com.crescer.stone_board.entity.Person;
import br.com.crescer.stone_board.entity.model.VoteModel;
import br.com.crescer.stone_board.repository.CardRepository;
import br.com.crescer.stone_board.repository.PersonRepository;
import br.com.crescer.stone_board.repository.VoteRepository;
import br.com.crescer.stone_board.utils.PersonComponent;
import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

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
    PersonRepository personRepository;
    @Autowired
    PersonComponent personComponent;

    public void save(VoteModel voteModel, Person person) {
        Card card = cardRepository.findOne(voteModel.getId_card());

        Vote vote = Vote.builder()
                .person(person)
                .positive(voteModel.isPositive())
                .build();

        if (CollectionUtils.isEmpty(card.getVotes())) {
            card.getVotes().add(vote);
        } else {
            Vote votePerson = card.getVotes()
                    .stream()
                    .filter(x -> Objects.equals(x.getPerson().getId(), person.getId()))
                    .findAny()
                    .orElse(null);

            if (votePerson == null) {
                card.getVotes().add(vote);
            } else if (votePerson.isPositive() == vote.isPositive()) {
                card.getVotes().remove(votePerson);
            } else {
                votePerson.setPositive(vote.isPositive());
                voteRepository.save(votePerson);
            }
        }

        cardRepository.save(card);
    }

}
