package br.com.crescer.stone_board.entity.model;

import br.com.crescer.stone_board.entity.Board;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
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
    private String title;
    private LocalDateTime deadline;
    private List<PersonModel> members;
    private List<BoardSessionModel> sessions;

    public static BoardModel convertToBoardModel(Board board) {
        return BoardModel.builder()
                .id(board.getId())
                .title(board.getTitle())
                .deadline(board.getDeadline())
                .members(board.getMembers().stream()
                        .map(PersonModel::convertToPersonModel).collect(Collectors.toList()))
                .sessions(board.getSessions().stream()
                        .map(BoardSessionModel::convertToBoardSessionModel)
                        .collect(Collectors.toList()))
                .build();
    }
}
