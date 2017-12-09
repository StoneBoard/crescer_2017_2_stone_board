package br.com.crescer.stone_board.service;

import br.com.crescer.stone_board.entity.Board;
import br.com.crescer.stone_board.entity.Person;
import br.com.crescer.stone_board.entity.model.BoardModel;
import br.com.crescer.stone_board.repository.BoardRepository;
import br.com.crescer.stone_board.repository.PersonRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
     
     
     public Board save(BoardModel boardModel){
         Board board = BoardModel.convertToBoard(boardModel);
         List<Long> members = boardModel.getId_members();
         if (!members.isEmpty()){
           List<Person> membersBoard = personRepository.findByIdIn(members);
           board.getMembers().addAll(membersBoard);
         }      
         return boardRepository.save(board);
     }
}
