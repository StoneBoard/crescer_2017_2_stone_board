package br.com.crescer.stone_board.entity.model;

import br.com.crescer.stone_board.entity.BoardSession;
import java.io.Serializable;
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
public class BoardSessionModel implements Serializable{
    @NotNull(message = "error.title.notnull ")
    @Size(min = 1, max = 128, message = "erro.titulo.size")
    private String title;
    @NotNull(message = "error.color.notnull")
    private int color;
    @NotNull(message = "error.icon.notnull")
    @Size(max = 512, message = "error.icon.size")
    private String icon;
    private List<CardModel> cards;
    
    public static BoardSession convertToBoardSession(BoardSessionModel boardSessionModel) {
       return BoardSession.builder()
               .title(boardSessionModel.getTitle())
               .color(boardSessionModel.getColor())
               .icon(boardSessionModel.getIcon())
               .build();
    }
    
    public static BoardSessionModel convertToBoardSessionModel(BoardSession boardSession) {
        return BoardSessionModel.builder()
                .title(boardSession.getTitle())
                .color(boardSession.getColor())
                .icon(boardSession.getIcon())
                .cards(boardSession.getCards()
                        .stream()
                        .map(CardModel::convertToCardModel)
                        .collect(Collectors.toList()))
                .build();
    }
    
}
