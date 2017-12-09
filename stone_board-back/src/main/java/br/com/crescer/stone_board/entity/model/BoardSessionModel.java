package br.com.crescer.stone_board.entity.model;

import br.com.crescer.stone_board.entity.BoardSession;
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
public class BoardSessionModel implements Serializable{
    @NotNull(message = "error.title.notnull")
    @Size(max = 128, message = "erro.titulo.size")
    private String title;
    @NotNull(message = "error.color.notnull")
    private int color;
    @NotNull(message = "error.icon.notnull")
    @Size(max = 512, message = "error.icon.size")
    private String icon;
    private List<Long> id_cards;
    
    
     public static BoardSession convertToBoardSession(BoardSessionModel boardSessionModel) {
       return BoardSession.builder()
               .title(boardSessionModel.getTitle())
               .color(boardSessionModel.getColor())
               .icon(boardSessionModel.getIcon())
               .build();
   }
    
}
