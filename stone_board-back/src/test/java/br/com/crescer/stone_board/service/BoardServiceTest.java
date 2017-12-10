
package br.com.crescer.stone_board.service;

import br.com.crescer.stone_board.entity.Board;
import br.com.crescer.stone_board.repository.BoardRepository;
import java.time.LocalDateTime;
import java.time.Month;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 *
 * @author willian
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class BoardServiceTest {

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
        boardRepository.save(getBoard());
       
        Board result = boardService.findById(getBoard().getId());
//        assertEquals(getBoard().getDeadline(), result.getDeadline());
        assertEquals(getBoard().getTitle(), result.getTitle());
        assertEquals(getBoard().isActive(), result.isActive());
    }
    
    
     @Test
    public void testFindByEmailWithNull() {
        boardRepository.save(getBoard());

        Board result = boardService.findById(new Long(200));
        assertNull(result);
    }
    
    private Board getBoard() {
        return Board.builder()
               .id(new Long(1))
               .title("Minha Board")
               .deadline(LocalDateTime.of(2017, Month.DECEMBER, 1, 10, 10, 30))
               .active(true)
               .build();
    }
    
    

}