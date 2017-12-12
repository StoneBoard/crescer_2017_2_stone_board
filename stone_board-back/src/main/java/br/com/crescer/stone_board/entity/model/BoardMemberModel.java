/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.crescer.stone_board.entity.model;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author marcele.dorneles
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BoardMemberModel implements Serializable{
    private BoardModel board;
    private PersonModel person;
}