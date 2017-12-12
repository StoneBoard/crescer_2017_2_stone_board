package br.com.crescer.stone_board.controller;

import br.com.crescer.stone_board.entity.model.NotificationModel;
import br.com.crescer.stone_board.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author marcele.dorneles
 */
@RestController
@RequestMapping("/notification")
public class NotificationController {
    
    @Autowired
    private NotificationService notificationService;
    
    @PostMapping
    public void Save(@Validated @RequestBody NotificationModel notificationModel){
       notificationService.save(notificationModel);
    }
    
}
