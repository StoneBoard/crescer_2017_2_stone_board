/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.crescer.stone_board.service;

import br.com.crescer.stone_board.entity.Person;
import br.com.crescer.stone_board.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Júlia
 */
@Service
public class PersonService {
    
    @Autowired
    PersonRepository personRepository;
    
    public Person findByEmail(String email){
        return personRepository.findByEmail(email);
    }
}
