package br.com.crescer.stone_board.repository;

import br.com.crescer.stone_board.entity.BoardSession;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Marcele Dorneles
 */
public interface BoardSessionRepository extends JpaRepository<BoardSession, Long> {

    List<BoardSession> findByIdIn(List<Long> ids);
}
