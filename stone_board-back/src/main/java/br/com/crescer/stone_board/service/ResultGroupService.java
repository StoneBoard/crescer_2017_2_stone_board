/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.crescer.stone_board.service;

import br.com.crescer.stone_board.entity.model.ResultGroupModel;
import br.com.crescer.stone_board.repository.ResultGroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author marcele.dorneles
 */
@Service
public class ResultGroupService {
    @Autowired
    ResultGroupRepository resultGroupRepository;
    
    public void save(ResultGroupModel resultGroupModel){
        
    } 
    
}
