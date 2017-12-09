package br.com.crescer.stone_board.repository;

import br.com.crescer.stone_board.entity.Board;
import br.com.crescer.stone_board.entity.Person;
import java.util.List;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author Marcele Dorneles
 */
public interface BoardRepository extends CrudRepository<Board, Long>{
}
