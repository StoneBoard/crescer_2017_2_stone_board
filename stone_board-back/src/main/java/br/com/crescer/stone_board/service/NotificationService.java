/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.crescer.stone_board.service;

import br.com.crescer.stone_board.entity.Notification;
import br.com.crescer.stone_board.entity.model.NotificationModel;
import br.com.crescer.stone_board.repository.NotificationRepository;
import br.com.crescer.stone_board.repository.PersonRepository;
import br.com.crescer.stone_board.utils.PersonComponent;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author willian
 */
public class NotificationService {
    
    @Autowired
    PersonRepository personRepository;
    @Autowired
    NotificationRepository notificationRepository;
    @Autowired
    PersonComponent personComponent;
    
    
    
}
