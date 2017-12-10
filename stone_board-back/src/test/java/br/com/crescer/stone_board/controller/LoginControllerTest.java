package br.com.crescer.stone_board.controller;
import br.com.crescer.stone_board.Utils.ControllerTestConfiguration;
import br.com.crescer.stone_board.entity.Person;
import br.com.crescer.stone_board.repository.PersonRepository;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class LoginControllerTest extends ControllerTestConfiguration {

    @Autowired
    private PersonRepository personRepository;

    @Before
    public void setUp() {
        personRepository.deleteAll();
    }

    @Test
    @WithMockUser(username = "teste@teste.com", password = "teste")
    public void getLoggedPerson() throws Exception {
        Person personTest = personRepository.save(getPersonOne());
        mockMvc.perform(MockMvcRequestBuilders.get("/initial/login"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("id").value(personTest.getId()))
                .andExpect(jsonPath("fullName").value(personTest.getFullName()))
                .andExpect(jsonPath("email").value(personTest.getEmail()));
    }

    private Person getPersonOne() {
        return Person.builder()
                .fullName("Marcele Teste")
                .email("teste@teste.com")
                .pass("teste")
                .build();
    }

}