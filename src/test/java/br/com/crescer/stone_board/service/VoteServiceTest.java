/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.crescer.stone_board.service;

import br.com.crescer.stone_board.Utils.ConfigurationTest;
import br.com.crescer.stone_board.repository.VoteRepository;
import org.junit.Ignore;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author marcele.dorneles
 */
@Ignore
public class VoteServiceTest extends ConfigurationTest {

    @Autowired
    VoteService voteService;

    @Autowired
    VoteRepository voteRepository;

}
