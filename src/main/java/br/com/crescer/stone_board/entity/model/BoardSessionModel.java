package br.com.crescer.stone_board.entity.model;

import br.com.crescer.stone_board.entity.BoardSession;
import java.io.Serializable;
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
public class BoardSessionModel implements Serializable {

    private Long id;
    private String title;
    private int color;
    private String icon;
    private List<CardModel> cards;

    public static BoardSession convertToBoardSession(BoardSessionModel boardSessionModel) {
        return BoardSession.builder()
                .id(boardSessionModel.getId())
                .title(boardSessionModel.getTitle())
                .color(boardSessionModel.getColor())
                .icon(boardSessionModel.getIcon())
                .build();
    }

    public static BoardSessionModel convertToBoardSessionModel(BoardSession boardSession) {
        return BoardSessionModel.builder()
                .id(boardSession.getId())
                .title(boardSession.getTitle())
                .color(boardSession.getColor())
                .icon(boardSession.getIcon())
                .cards(boardSession.getCards().stream()
                        .map(c -> CardModel.convertToCardModel(c, boardSession.getId()))
                        .collect(Collectors.toList()))
                .build();
    }

}
