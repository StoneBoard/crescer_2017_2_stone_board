package br.com.crescer.stone_board.repository;

import br.com.crescer.stone_board.entity.Vote;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author marcele.dorneles
 */
public interface VoteRepository extends JpaRepository<Vote, Long> {

}
