package br.com.crescer.stone_board.repository;

import br.com.crescer.stone_board.entity.Person;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Júlia
 */
public interface PersonRepository extends JpaRepository<Person, Long> {

    Person findByEmail(String email);
    
    boolean existsByEmail(String email);

    List<Person> findByIdIn(List<Long> ids);

    List<Person> findByEmailContainingIgnoreCase(String email);

}
