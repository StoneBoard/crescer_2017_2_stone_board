package br.com.crescer.stone_board.service;

import br.com.crescer.stone_board.entity.Person;
import br.com.crescer.stone_board.repository.PersonRepository;
import br.com.crescer.stone_board.utils.ConfigurationTest;
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

    @Before
    public void setUp() {
        personRepository.deleteAll();
    }

    @Test
    public void testFindByEmail() {
        Person person = personRepository.save(getPersonOne());

        Person result = personService.findByEmail(getPersonOne().getEmail());

        assertEquals(person.getId(), result.getId());
        assertEquals(person.getFullName(), result.getFullName());
        assertEquals(person.getEmail(), result.getEmail());
        assertEquals(person.getPass(), result.getPass());

    }

    @Test
    public void testFindByEmailWithNull() {
        personRepository.save(getPersonOne());

        Person result = personService.findByEmail("julia@teste.com");

        assertNull(result);

    }

    private Person getPersonOne() {
        return Person.builder()
                .fullName("Willian Teste")
                .email("willian@teste.com")
                .pass("teste")
                .build();
    }

}