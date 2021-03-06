package br.com.crescer.stone_board.service;

import br.com.crescer.stone_board.entity.BoardSession;
import br.com.crescer.stone_board.entity.Card;
import br.com.crescer.stone_board.entity.Person;
import br.com.crescer.stone_board.entity.model.CardModel;
import br.com.crescer.stone_board.repository.BoardSessionRepository;
import br.com.crescer.stone_board.repository.CardRepository;
import br.com.crescer.stone_board.repository.PersonRepository;
import br.com.crescer.stone_board.utils.BadRequestException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 *
 * @author Marcele Dorneles
 */
@Service
public class CardService {

    @Autowired
    CardRepository cardRepository;
    
    @Autowired
    PersonRepository personRepository;
    
    @Autowired
    BoardSessionRepository boardSessionRepository;
    
    @Autowired
    BoardService boardService;

    public Card findById(Long id) {
        return cardRepository.findOne(id);
    }

    public List<Card> findAllCards() {
        return cardRepository.findAll();
    }

    public List<Card> findAllCardsOutsideResultGroup(Long idBoard) {
        List<Card> cards = new ArrayList<>();
        List<BoardSession> sessions = boardService.findById(idBoard).getSessions();

        sessions.forEach(session -> cards.addAll(session.getCards()));

        return cards.stream()
                .filter(p -> p.getResultGroup() == null)
                .collect(Collectors.toList());
    }

    public void save(CardModel cardModel, Person person) {
        Card card = CardModel.convertToCard(cardModel, person);
        BoardSession boardSession = boardSessionRepository.findOne(cardModel.getId_session());
        boardSession.getCards().add(card);
        boardSessionRepository.save(boardSession);
    }

    public void update(CardModel cardModel, Long personId) {
        Card card = cardRepository.findOne(cardModel.getId());
        if (card.getWriter().getId() != personId) {
            throw new BadRequestException("Apenas o usuário que criou o post it pode realizar esta ação.");
        }

        card.setText(cardModel.getText());
        cardRepository.save(card);
    }

    public void delete(Long idCard, Long idSession, Long personId) {
        Card card = cardRepository.findOne(idCard);
        if (card.getWriter().getId() != personId) {
            throw new BadRequestException("Apenas o usuário que criou o post it pode realizar esta ação.");
        }

        BoardSession session = boardSessionRepository.findOne(idSession);
        session.getCards().removeIf(c -> Objects.equals(c.getId(), idCard));
        boardSessionRepository.save(session);
    }
}
