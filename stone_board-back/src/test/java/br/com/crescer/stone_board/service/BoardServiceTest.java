
package br.com.crescer.stone_board.service;

import br.com.crescer.stone_board.Utils.ConfigurationTest;
import br.com.crescer.stone_board.entity.Board;
import br.com.crescer.stone_board.repository.BoardRepository;
import java.time.LocalDateTime;
import java.time.Month;
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
        Board board = boardRepository.save(getBoard());
        Board result = boardService.findById(board.getId());

        assertEquals(board.getTitle(), result.getTitle());
        assertEquals(board.isActive(), result.isActive());
        assertEquals(board.getDeadline(), result.getDeadline());
    }
    
    @Test
    public void testFindByIdWithNotExists() {
        assertNull(boardService.findById(200l));
    }
  
    
    private Board getBoard() {
        return Board.builder()
               .id(new Long(1))
               .title("Minha Board")
               .deadline(LocalDateTime.now())
               .active(true)
               .build();
    }

}

