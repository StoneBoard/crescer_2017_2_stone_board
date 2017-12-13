/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.crescer.stone_board.entity.model;

import br.com.crescer.stone_board.entity.Board;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author Júlia
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BoardRegisterModel {
    
    private Long id;
    @NotNull(message = "O Título não pode ser nulo")
    @Size(max = 128, min = 1, message = "O título não pode ter mais de 128 caracteres")
    private String title;
    @NotNull(message = "A Data limite não pode ser nula")
    private LocalDateTime deadline;
    @NotNull(message = "O Board deve possuir ao menos uma sessão")
    @Valid
    private List<BoardSessionRegisterModel> sessions;
    
    public static Board convertToBoard(BoardRegisterModel boardRegister){
        return Board.builder()
                .title(boardRegister.getTitle())
                .deadline(boardRegister.getDeadline())
                .sessions(boardRegister.getSessions()
                            .stream()
                            .map(BoardSessionRegisterModel::convertToBoardSession)
                            .collect(Collectors.toList()))
                .build();
    }
    
}
