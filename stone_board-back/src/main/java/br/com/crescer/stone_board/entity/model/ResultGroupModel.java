package br.com.crescer.stone_board.entity.model;

import br.com.crescer.stone_board.entity.Board;
import br.com.crescer.stone_board.entity.Person;
import br.com.crescer.stone_board.entity.ResultGroup;
import java.io.Serializable;
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
public class ResultGroupModel implements Serializable{
    @NotNull(message = "erro.title.notnull")
    @Size(max =128, message = "erro.title.size ")
    private String title;
    @NotNull(message ="erro.description.notnull")
    @Size(max = 500, message = "erro.description.size")
    private String description;
    private List<Long> id_cards;
    private long id_board;
    
    public static ResultGroup convertToNote(ResultGroupModel resultGroupModel, Board board) {
       return ResultGroup.builder()
               .title(resultGroupModel.getTitle())
               .description(resultGroupModel.getDescription())
               .board(board)
               .build();
   }
}
