/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.crescer.stone_board.service;

import br.com.crescer.stone_board.entity.Board;
import br.com.crescer.stone_board.entity.Notification;
import br.com.crescer.stone_board.entity.Person;
import br.com.crescer.stone_board.repository.PersonRepository;
import br.com.crescer.stone_board.utils.PersonComponent;
import java.util.List;
import java.util.stream.Collectors;
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
    @Autowired
    PersonComponent personComponent;
    
    public Person findByEmail(String email){
        return personRepository.findByEmail(email);
    }
    
    public List<Board> listMyBoards() {        
        return personComponent.loggedPersonDetails().getMyBoards();
    }
    public List<Board> listConnectBoards() {
        return personComponent.loggedPersonDetails().getConnectBoards();
    }
    
    public List<Notification> listMyNotifications() {        
        return personComponent.loggedPersonDetails().getNotifications();
    }
<<<<<<< HEAD
=======
    public List<PersonModel> findPersonsByEmail(String email){
        return personRepository.findByEmailContainingIgnoreCase(email)
                .stream()
                .map(PersonModel :: convertToPersonModel)
                .collect(Collectors.toList());
    }
     
>>>>>>> 1ba69af413c00e3067a420cb1bcee302d796735f
}
