package br.com.crescer.stone_board.controller;

import br.com.crescer.stone_board.entity.Board;
import br.com.crescer.stone_board.repository.BoardRepository;
import br.com.crescer.stone_board.Utils.ConfigurationTest;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
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
    
    @Before
    public void setUp() {
        boardRepository.deleteAll();
    }
    
    @Test
    @WithMockUser(username = "teste@teste.com", password = "teste")
    public void createBoard() throws Exception {
        Board boardTest = boardRepository.save(getBoardOne());
        mockMvc.perform(MockMvcRequestBuilders.get("/board/findById/{id}", boardTest.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("id").value(boardTest.getId()))
                .andExpect(jsonPath("title").value(boardTest.getTitle()))
                .andExpect(jsonPath("deadline").value(boardTest.getDeadline().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"))));
    }
    
    private Board getBoardOne() {
        return Board.builder()
                .title("Teste")
                .deadline(LocalDateTime.now())
                .build();
    }
    
}