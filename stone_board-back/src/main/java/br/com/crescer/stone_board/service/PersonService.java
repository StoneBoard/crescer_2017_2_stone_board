/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.crescer.stone_board.service;

import br.com.crescer.stone_board.entity.Board;
import br.com.crescer.stone_board.entity.Person;
import br.com.crescer.stone_board.entity.model.LoggedPersonModel;
import br.com.crescer.stone_board.repository.PersonRepository;
import br.com.crescer.stone_board.utils.PersonComponent;
import java.util.List;
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
        LoggedPersonModel personLogedModel = personComponent.loggedPerson();
        Person personLoged = personRepository.findOne(personLogedModel.getId());
        return personRepository.findOne(personLoged.getId()).getMyBoards();
    }
    public List<Board> listConnectBoards() {
        LoggedPersonModel personLogedModel = personComponent.loggedPerson();
        Person personLoged = personRepository.findOne(personLogedModel.getId());
        return personRepository.findOne(personLoged.getId()).getConnectBoards();
    }
    
}
