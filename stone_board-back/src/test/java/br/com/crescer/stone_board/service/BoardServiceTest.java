package br.com.crescer.stone_board.service;

import br.com.crescer.stone_board.entity.Board;
import br.com.crescer.stone_board.repository.BoardRepository;
import br.com.crescer.stone_board.Utils.ConfigurationTest;
import br.com.crescer.stone_board.entity.BoardSession;
import br.com.crescer.stone_board.entity.Person;
import br.com.crescer.stone_board.entity.model.BoardRegisterModel;
import br.com.crescer.stone_board.entity.model.BoardSessionRegisterModel;
import br.com.crescer.stone_board.repository.PersonRepository;
import br.com.crescer.stone_board.utils.BadRequestException;
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

    @Autowired
    PersonService personService;

    @Autowired
    PersonRepository personRepository;

    @Before
    public void setUp() {
        personRepository.deleteAll();
    }

    @Test(expected = BadRequestException.class)
    public void testFailedToSaveWithIncorrectDeadline() {
        BoardRegisterModel boardRegistarModel = DataGenerator.createBoardRegisterModel();
        Person person = DataGenerator.createPerson();
        personRepository.save(person);
        boardRegistarModel.setDeadline(LocalDateTime.now());
        boardService.save(boardRegistarModel, person);

    }

    @Test
    public void testFindByIdWithNotExists() {
        assertNull(boardService.findById(200l));
    }
}
