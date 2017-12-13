/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.crescer.stone_board.entity.model;

import br.com.crescer.stone_board.entity.Board;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author willian.pazinatto
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RegisterBoardModel implements Serializable {

    private Long id;
    @NotNull(message = "error.title.notnull ")
    @Size(max = 128, min = 1, message = "error.title.size ")
    private String title;
    private LocalDateTime deadline;
    private List<BoardSessionModel> sessions;

    public static Board convertToBoard(RegisterBoardModel boardModel) {
        return Board.builder()
                .title(boardModel.getTitle())
                .deadline(boardModel.getDeadline())
                .build();
    }

    public static RegisterBoardModel convertToBoardModel(Board board) {
        return RegisterBoardModel.builder()
                .id(board.getId())
                .title(board.getTitle())
                .deadline(board.getDeadline())
                .sessions(board.getSessions().stream()
                        .map(BoardSessionModel::convertToBoardSessionModel)
                        .collect(Collectors.toList()))
                .build();
    }
}
