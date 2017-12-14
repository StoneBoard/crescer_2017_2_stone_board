package br.com.crescer.stone_board.service;

import br.com.crescer.stone_board.entity.BoardSession;
import br.com.crescer.stone_board.entity.model.BoardSessionModel;
import br.com.crescer.stone_board.repository.BoardSessionRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Marcele Dorneles
 */
@Service
public class BoardSessionService {
     @Autowired
     BoardSessionRepository boardSessionRepository;
     
    public BoardSession findById(Long id) {
         return boardSessionRepository.findOne(id);
    }
     
    public List<BoardSession> findAllBoards() {
         return boardSessionRepository.findAll();
    }
    
    public BoardSession save(BoardSessionModel boardSessionModel){
         BoardSession boardSession = BoardSessionModel.convertToBoardSession(boardSessionModel);
         return boardSessionRepository.save(boardSession);
    }
}
