package br.com.crescer.stone_board.controller;

import br.com.crescer.stone_board.repository.BoardRepository;
import br.com.crescer.stone_board.Utils.ConfigurationTest;
import br.com.crescer.stone_board.entity.Person;
import br.com.crescer.stone_board.entity.model.BoardRegisterModel;
import br.com.crescer.stone_board.entity.model.BoardSessionRegisterModel;
import br.com.crescer.stone_board.repository.PersonRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.time.LocalDateTime;
import java.util.Arrays;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 *
 * @author willian
 */
public class BoardControllerTest extends ConfigurationTest {

    @Autowired
    private BoardRepository boardRepository;

    @Autowired
    private PersonRepository personRepository;

    @Before
    public void setUp() {
        boardRepository.deleteAll();
        personRepository.deleteAll();
        personRepository.save(getPersonOne());
    }

    @Test
    @WithMockUser(username = "teste@teste.com", password = "teste")
    public void saveBoard() throws Exception {
        String content = contentPost(newBoard());

        mockMvc.perform(MockMvcRequestBuilders.post("/board")
                .content(content)
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username = "teste@teste.com", password = "teste")
    public void saveBoardWithDeadlineNow() throws Exception {
        String content = contentPost(newBoardDeadlineNow());

        mockMvc.perform(MockMvcRequestBuilders.post("/board")
                .content(content)
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(status().isBadRequest());
    }

    private Person getPersonOne() {
        return Person.builder()
                .fullName("Marcele Teste")
                .email("teste@teste.com")
                .pass("teste")
                .build();
    }

    private BoardRegisterModel newBoard() {
        BoardRegisterModel board = new BoardRegisterModel();
        board.setDeadline(LocalDateTime.now().plusDays(10));
        board.setTitle("Nova Board");
        board.setSessions(Arrays.asList(new BoardSessionRegisterModel("teste", 1, "teste")));
        return board;
    }

    private BoardRegisterModel newBoardDeadlineNow() {
        BoardRegisterModel board = new BoardRegisterModel();
        board.setDeadline(LocalDateTime.now());
        board.setTitle("Nova Board");
        board.setSessions(Arrays.asList(new BoardSessionRegisterModel("teste", 1, "teste")));
        return board;
    }

    private String contentPost(BoardRegisterModel model) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.findAndRegisterModules();
        return mapper.writeValueAsString(model);
    }

}
