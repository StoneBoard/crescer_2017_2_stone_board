/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.crescer.stone_board.service;

import br.com.crescer.stone_board.entity.Notification;
import br.com.crescer.stone_board.entity.Person;
import br.com.crescer.stone_board.entity.model.NotificationModel;
import br.com.crescer.stone_board.repository.NotificationRepository;
import br.com.crescer.stone_board.repository.PersonRepository;
import br.com.crescer.stone_board.utils.PersonComponent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author willian
 */
@Service
public class NotificationService {
    
    @Autowired
    PersonRepository personRepository;
    @Autowired
    NotificationRepository notificationRepository;
    @Autowired
    PersonComponent personComponent;
    
    public void save(NotificationModel notificationModel){
        Person person = personRepository.findByEmail(notificationModel.getPerson().getEmail());
        Notification notification = NotificationModel.convertToNotification(notificationModel);
        person.getNotifications().add(notification);
        personRepository.save(person);
    }
    
    
}
