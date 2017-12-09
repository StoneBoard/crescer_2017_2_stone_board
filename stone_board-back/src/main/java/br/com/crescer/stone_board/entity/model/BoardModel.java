package br.com.crescer.stone_board.entity.model;

import br.com.crescer.stone_board.entity.Board;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author Marcele Dorneles
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BoardModel implements Serializable{
    @NotNull(message = "erro.nome.notnull")
    @Size(max = 128, message = "erro.titulo.size")
    private String title;
  
    private LocalDateTime deadline;
    private boolean active;
    private List<Long> id_members;
    private List<Long> id_sessions;
    
    public static Board convertToBoard(BoardModel boardModel) {
       return Board.builder()
               .title(boardModel.getTitle())
               .deadline(boardModel.getDeadline())
               .active(boardModel.isActive())
               .build();
   }
}
