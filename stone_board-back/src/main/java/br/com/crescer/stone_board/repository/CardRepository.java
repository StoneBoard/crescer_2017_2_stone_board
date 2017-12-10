package br.com.crescer.stone_board.repository;

import br.com.crescer.stone_board.entity.Card;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Marcele Dorneles
 */
public interface CardRepository extends JpaRepository<Card, Long>{
    
}
