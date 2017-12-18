package br.com.crescer.stone_board.service;

import br.com.crescer.stone_board.entity.Person;
import br.com.crescer.stone_board.repository.PersonRepository;
import br.com.crescer.stone_board.Utils.ConfigurationTest;
import br.com.crescer.stone_board.entity.Board;
import br.com.crescer.stone_board.repository.BoardRepository;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author willian
 */
public class PersonServiceTest extends ConfigurationTest {

    @Autowired
    PersonService personService;

    @Autowired
    PersonRepository personRepository;
    
    @Autowired
    BoardService boardService;
    
    @Autowired
    BoardRepository boardRepository;

    @Before
    public void setUp() {
        personRepository.deleteAll();
    }

    @Test
    public void testFindByEmail() {
        Person person = DataGenerator.createPerson();
        personRepository.save(person);
        
        Person result = personService.findByEmail(person.getEmail());

        assertEquals(person.getId(), result.getId());
        assertEquals(person.getFullName(), result.getFullName());
        assertEquals(person.getEmail(), result.getEmail());
        assertEquals(person.getPass(), result.getPass());
        
        personRepository.delete(person.getId());
    }
    
    @Test
    public void testListConectedBoard() {
        Person person = DataGenerator.createPerson();
        personRepository.save(person);
        Board board = DataGenerator.createBoard();
        boardRepository.save(board);
        
        List<Board> result = personService.listMyBoards(person);

        
        personRepository.delete(person.getId());
    }


    @Test
    public void testFindByEmailWithNull() {
        personRepository.save(DataGenerator.createPerson());

        Person result = personService.findByEmail("julia@teste.com");

        assertNull(result);
    }
}