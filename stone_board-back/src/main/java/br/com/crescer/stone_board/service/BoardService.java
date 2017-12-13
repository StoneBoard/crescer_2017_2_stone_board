package br.com.crescer.stone_board.service;

import br.com.crescer.stone_board.entity.Board;
import br.com.crescer.stone_board.entity.BoardSession;
import br.com.crescer.stone_board.entity.Person;
import br.com.crescer.stone_board.entity.model.BoardMemberModel;
import br.com.crescer.stone_board.entity.model.BoardModel;
import br.com.crescer.stone_board.entity.model.BoardSessionModel;
import br.com.crescer.stone_board.repository.BoardRepository;
import br.com.crescer.stone_board.repository.BoardSessionRepository;
import br.com.crescer.stone_board.repository.PersonRepository;
import br.com.crescer.stone_board.utils.PersonComponent;

import java.util.List;
import java.util.Objects;
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
    PersonService personService;
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
    
    public void addMembers(BoardMemberModel boardMemberModel){
        Person person = personRepository.findOne(boardMemberModel.getId_person());
        Board board = boardRepository.findOne(boardMemberModel.getId_board());
        person.getConnectBoards().add(board);
        personRepository.save(person);     
    }
    
    public BoardModel update(BoardModel boardModel){
        Board board = boardRepository.findOne(boardModel.getId());   
        board.setTitle(boardModel.getTitle());
        board.setDeadline(boardModel.getDeadline());
        boardRepository.save(board);
        return BoardModel.convertToBoardModel(board);
        
    }
    
    public boolean userAuthenticadedBoard(Long id){
        Board board = findById(id);
        Person personLoged = personComponent.loggedPersonDetails();
        return board.getMembers().stream().anyMatch(x -> Objects.equals(x.getId(), personLoged.getId()))
            || personLoged.getMyBoards().stream().anyMatch(x -> Objects.equals(x.getId(),id));     
    }
    
}
