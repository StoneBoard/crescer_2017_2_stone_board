/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.crescer.stone_board.repository;

import br.com.crescer.stone_board.entity.BoardSession;
import java.util.List;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author Marcele Dorneles
 */
public interface BoardSessionRepository  extends CrudRepository<BoardSession, Long> {
    List<BoardSession> findByIdIn (List<Long> ids);
}
