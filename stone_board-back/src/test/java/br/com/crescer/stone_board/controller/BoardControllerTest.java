package br.com.crescer.stone_board.controller;

import Utils.ControllerTestConfiguration;
import br.com.crescer.stone_board.entity.Board;
import br.com.crescer.stone_board.repository.BoardRepository;
import java.time.LocalDateTime;
import java.time.Month;
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


public class BoardControllerTest extends ControllerTestConfiguration {

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
        mockMvc.perform(MockMvcRequestBuilders.get("/board"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("id").value(boardTest.getId()))
                .andExpect(jsonPath("title").value(boardTest.getTitle()))
                .andExpect(jsonPath("email").value(boardTest.isActive()));
    }

    private Board getBoardOne() {
        return Board.builder()
                .title("Teste")
                .deadline(LocalDateTime.of(2017, Month.DECEMBER, 1, 10, 10, 30))
                .active(true)
                .build();
    }

};