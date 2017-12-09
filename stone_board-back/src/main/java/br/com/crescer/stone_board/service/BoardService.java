package br.com.crescer.stone_board.service;

import br.com.crescer.stone_board.entity.Board;
import br.com.crescer.stone_board.entity.Person;
import br.com.crescer.stone_board.entity.model.BoardModel;
import br.com.crescer.stone_board.entity.model.LoggedPersonModel;
import br.com.crescer.stone_board.repository.BoardRepository;
import br.com.crescer.stone_board.repository.PersonRepository;
import br.com.crescer.stone_board.utils.PersonComponent;
import java.util.List;
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
     
     
     public Board save(BoardModel boardModel){
         Board board = BoardModel.convertToBoard(boardModel);
   
         List<Long> members = boardModel.getId_members();
         if (!CollectionUtils.isEmpty(members)){
           List<Person> membersBoard = personRepository.findByIdIn(members);
           board.getMembers().addAll(membersBoard);
         }      
         
         LoggedPersonModel personLogedModel = personComponent.loggedPerson();
         Person personLoged = personRepository.findOne(personLogedModel.getId());
         personLoged.getMyBoards().add(board);
         
         return boardRepository.save(board);
     }
}
