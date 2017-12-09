/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.crescer.stone_board.entity.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author Marcele Dorneles
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MessageErrorModel {

    public String menssage;
    public String type;
}