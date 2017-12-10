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
 * @author Marcele Dorneles
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BoardModel implements Serializable{
    @NotNull(message = "error.title.notnull ")
    @Size(max = 128,min = 1, message = "error.title.size ")
    private String title;
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
    private LocalDateTime deadline;
    private boolean active;
    private List<Long> id_members;
    private List<BoardSessionModel> sessions;
    
    public static Board convertToBoard(BoardModel boardModel) {
       return Board.builder()
               .title(boardModel.getTitle())
               .deadline(boardModel.getDeadline())
               .active(boardModel.isActive())
               .build();
   }
    
    public static BoardModel convertToBoardModel(Board board) {
        return BoardModel.builder()
                .title(board.getTitle())
                .deadline(board.getDeadline())
                .active(board.isActive())
                .id_members(board.getMembers()
                            .stream()
                            .map(member -> member.getId())
                            .collect(Collectors.toList()))
                .sessions(board.getSessions()
                            .stream()
                            .map(BoardSessionModel::convertToBoardSessionModel)
                            .collect(Collectors.toList()))
                .build();
    }
}
