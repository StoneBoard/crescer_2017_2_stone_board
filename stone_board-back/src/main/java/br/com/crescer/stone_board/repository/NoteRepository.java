package br.com.crescer.stone_board.repository;

import br.com.crescer.stone_board.entity.Note;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author willian
 */
public interface NoteRepository extends JpaRepository<Note, Long> {

}
