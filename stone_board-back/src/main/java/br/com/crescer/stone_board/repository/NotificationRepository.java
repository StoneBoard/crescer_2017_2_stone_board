/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.crescer.stone_board.repository;

import br.com.crescer.stone_board.entity.Notification;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author willian
 */
public interface NotificationRepository extends JpaRepository<Notification, Long> {
    
}
