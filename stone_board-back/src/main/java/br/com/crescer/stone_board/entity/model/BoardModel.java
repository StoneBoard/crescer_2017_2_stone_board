package br.com.crescer.stone_board.entity.model;

import br.com.crescer.stone_board.entity.Board;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
 * @author Marcele Dorneles
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BoardModel implements Serializable {

    private Long id;
    @NotNull(message = "error.title.notnull ")
    @Size(max = 128, min = 1, message = "error.title.size ")
    private String title;
    private LocalDateTime deadline;
    private List<Long> id_members;
    private List<BoardSessionModel> sessions;

    public static Board convertToBoard(BoardModel boardModel) {
        return Board.builder()
                .title(boardModel.getTitle())
                .deadline(boardModel.getDeadline())
                .build();
    }

    public static BoardModel convertToBoardModel(Board board) {
        return BoardModel.builder()
                .id(board.getId())
                .title(board.getTitle())
                .deadline(board.getDeadline())
                .id_members(board.getMembers().stream()
                        .map(x -> x.getId()).collect(Collectors.toList()))
                .sessions(board.getSessions().stream()
                        .map(BoardSessionModel::convertToBoardSessionModel)
                        .collect(Collectors.toList()))
                .build();
    }
}
