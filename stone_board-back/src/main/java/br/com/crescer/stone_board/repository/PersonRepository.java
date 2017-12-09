/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.crescer.stone_board.repository;

import br.com.crescer.stone_board.entity.Person;
import java.util.List;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author Júlia
 */
public interface PersonRepository extends CrudRepository<Person, Long> {

    Person findByEmail(String email);
    List<Person> findByIdIn (List<Long> ids);   
    
}
