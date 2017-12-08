/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.crescer.stone_board.entity.model;

import br.com.crescer.stone_board.entity.Person;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author Júlia
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LoggedPersonModel {
    
    private Long id;
    private String fullName;
    private String email;
    
    public static LoggedPersonModel convertToLoggedPerson(Person person){
        return LoggedPersonModel.builder()
                .id(person.getId())
                .fullName(person.getFullName())
                .email(person.getEmail())
                .build();
    }
}
