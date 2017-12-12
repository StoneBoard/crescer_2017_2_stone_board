package br.com.crescer.stone_board.service;

import br.com.crescer.stone_board.entity.Board;
import br.com.crescer.stone_board.repository.BoardRepository;
import br.com.crescer.stone_board.Utils.ConfigurationTest;
import br.com.crescer.stone_board.entity.BoardSession;
import br.com.crescer.stone_board.entity.Person;
import br.com.crescer.stone_board.repository.PersonRepository;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Ignore;
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
    
    @Autowired
    PersonRepository personRepository;

    @Before
    public void setUp() {
        personRepository.deleteAll();
    }

    @Test
    @Ignore
    public void testSave() {        
        List<BoardSession> sessions = new ArrayList();
        sessions.add(DataGenerator.createBoardSesssion());
        sessions.add(DataGenerator.createBoardSesssion());

        Board board = DataGenerator.createBoard();
        board.getSessions().addAll(sessions);
        
        Person person = DataGenerator.createPerson();
        person.getMyBoards().add(board);
        
        personRepository.save(person);
        
        Board result = boardService.findById(board.getId());

        assertEquals(board.getTitle(), result.getTitle());
        assertEquals(board.getDeadline(), result.getDeadline());
    }

    @Test
    public void testFindByIdWithNotExists() {
        assertNull(boardService.findById(200l));
    }
}