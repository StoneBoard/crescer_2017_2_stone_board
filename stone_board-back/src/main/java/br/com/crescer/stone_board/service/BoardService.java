package br.com.crescer.stone_board.service;

import br.com.crescer.stone_board.entity.Board;
import br.com.crescer.stone_board.entity.BoardSession;
import br.com.crescer.stone_board.entity.Person;
import br.com.crescer.stone_board.entity.model.BoardMemberModel;
import br.com.crescer.stone_board.entity.model.BoardModel;
import br.com.crescer.stone_board.entity.model.BoardSessionModel;
import br.com.crescer.stone_board.entity.model.LoggedPersonModel;
import br.com.crescer.stone_board.entity.model.NotificationModel;
import br.com.crescer.stone_board.entity.model.PersonModel;
import br.com.crescer.stone_board.repository.BoardRepository;
import br.com.crescer.stone_board.repository.BoardSessionRepository;
import br.com.crescer.stone_board.repository.PersonRepository;
import br.com.crescer.stone_board.utils.PersonComponent;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

/**
 *
 * @author Marcele Dorneles
 */
@Service
public class BoardService {

    @Autowired
    BoardRepository boardRepository;
    @Autowired
    PersonRepository personRepository;
    @Autowired
    PersonComponent personComponent;
    @Autowired
    BoardSessionRepository boardSessionRepository;

    public Long save(BoardModel boardModel) {
        Board board = BoardModel.convertToBoard(boardModel);
        
        if (!CollectionUtils.isEmpty(boardModel.getSessions())) {
            List<BoardSession> boardSessions = boardModel.getSessions()
                                                .stream()
                                                .map(BoardSessionModel::convertToBoardSession)
                                                .collect(Collectors.toList());

            board.setSessions(boardSessions);
        }

        Person personLoged = personComponent.loggedPersonDetails();
        personLoged.getMyBoards().add(board);

        personRepository.save(personLoged);
        
        return board.getId();
    }
    public Board findById(Long id) {
        return boardRepository.findOne(id);
    }

    public List<Board> findAllBoards() {
        return boardRepository.findAll();
    }
    
    public PersonModel addMembers(BoardMemberModel boardMemberModel){
        Person person = personRepository.findByEmail(boardMemberModel.getPerson().getEmail());
        Board board = boardRepository.findOne(boardMemberModel.getBoard().getId());
        person.getConnectBoards().add(board);
        personRepository.save(person);  
        return PersonModel.convertToPersonModel(person);
    }
    public BoardModel update(BoardModel boardModel){
        Board board = boardRepository.findOne(boardModel.getId());   
        board.setDeadline(boardModel.getDeadline());
        return BoardModel.convertToBoardModel(board);
        
    }
}
