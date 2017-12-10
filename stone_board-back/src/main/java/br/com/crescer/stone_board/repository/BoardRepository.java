package br.com.crescer.stone_board.repository;

import br.com.crescer.stone_board.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Marcele Dorneles
 */
public interface BoardRepository extends JpaRepository<Board, Long>{
   
    
}
