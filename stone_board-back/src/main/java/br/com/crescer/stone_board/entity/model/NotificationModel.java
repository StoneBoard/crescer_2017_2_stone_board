/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.crescer.stone_board.entity.model;

import br.com.crescer.stone_board.entity.Board;
import br.com.crescer.stone_board.entity.Card;
import br.com.crescer.stone_board.entity.Notification;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author willian
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NotificationModel implements Serializable{
    
    private Long id;
    private LocalDateTime creationDate;
    private boolean checked;
    private BoardModel board;
    private PersonModel person;

    

       public static Notification convertToNotification(NotificationModel notificationModel) {
       return Notification.builder()
               .id(notificationModel.getId())
               .checked(false)
               .build();
   }
       ;

    
}
