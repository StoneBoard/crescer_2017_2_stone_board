/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.crescer.stone_board.service;

import br.com.crescer.stone_board.entity.Board;
import br.com.crescer.stone_board.entity.Person;
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
        //Limpa o database a cada teste
        boardRepository.deleteAll();
    }

    @Test
    public void testFindByEmail() {
        //Adiciona o objeto person
        boardRepository.save(getBoard());

        //Realiza a pesquisa
        Board result = boardService.save(getPersonOne().getEmail());

        //Realiza as comparações
        assertEquals(getPersonOne().getFullName(), result.getFullName());
        assertEquals(getPersonOne().getEmail(), result.getEmail());
        assertEquals(getPersonOne().getPass(), result.getPass());

    }

    @Test
    public void testFindByEmailWithNull() {
        //Adiciona o objeto person
        boardRepository.save(getPersonOne());

        //Realiza a pesquisa
        Person result = boardService.findByEmail("julia@teste.com");

        //Realiza as comparações
        assertNull(result);

    }

    private Board getBoard() {
        return Board.builder()
              .title("Minha Board")
               .deadline(LocalDateTime.of(2017, Month.DECEMBER, 1, 10, 10, 30))
               .active(true)
               .build();
    }

}