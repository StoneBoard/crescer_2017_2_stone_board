package br.com.crescer.stone_board.service;

import br.com.crescer.stone_board.entity.Board;
import br.com.crescer.stone_board.repository.BoardRepository;
import br.com.crescer.stone_board.Utils.ConfigurationTest;
import br.com.crescer.stone_board.entity.BoardSession;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author willian
 */
public class BoardServiceTest extends ConfigurationTest {

    @Autowired
    BoardService boardService;

    @Autowired
    BoardRepository boardRepository;

    @Before
    public void setUp() {
        boardRepository.deleteAll();
    }

    @Test
    public void testSave() {
        List<BoardSession> sessions = new ArrayList();
        sessions.add(getBoardSession());
        Board board = boardRepository.save(getBoard(sessions));
        Board result = boardService.findById(board.getId());

        assertEquals(board.getTitle(), result.getTitle());
    //    assertEquals(board.isActive(), result.isActive());
        assertEquals(board.getDeadline(), result.getDeadline());
    }

    @Test
    public void testFindByIdWithNotExists() {
        assertNull(boardService.findById(200l));
    }
    
    private BoardSession getBoardSession(){
        return BoardSession.builder()
                .title("Teste")
                .color(1)
                .build();
                
    }
    private Board getBoard(List<BoardSession> sessions) {
        return Board.builder()
                .title("Minha Board")
                .deadline(LocalDateTime.now())
                .sessions(sessions)
          //      .active(true)
                .build();
        
    }

}