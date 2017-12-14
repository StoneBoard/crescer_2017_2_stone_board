/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.crescer.stone_board.entity.model;

import br.com.crescer.stone_board.entity.Board;
import br.com.crescer.stone_board.entity.ResultGroup;
import java.io.Serializable;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author marcele.dorneles
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResultGroupRegisterModel implements Serializable
{
    private Long id;
    @NotNull(message = "O Título não pode ser nulo")
    @Size(max =128, message = "O título não pode ultrapassar 128 caracteres.")
    private String title;
    @NotNull(message ="A Descrição não pode ser nula")
    @Size(max = 500, message = "A descrição não pode ultrapassar 500 caracteres.")
    private String description;
    private long id_board;
    
    public static ResultGroup convertToResultGroup(ResultGroupRegisterModel resultGroupRegisterModel, Board board){
        return ResultGroup.builder()
                .title(resultGroupRegisterModel.getTitle())
                .description(resultGroupRegisterModel.getDescription())
                .board(board)
                .build();
    }
}
