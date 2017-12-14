package br.com.crescer.stone_board.entity.model;

import br.com.crescer.stone_board.entity.Board;
import br.com.crescer.stone_board.entity.Person;
import br.com.crescer.stone_board.entity.ResultGroup;
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
public class ResultGroupModel implements Serializable{
    private Long id;
    private String title;
    private String description;
    private List<CardModel> cards;
       
    public static ResultGroupModel convertToResultGroupModel(ResultGroup resultGroup) {
       return ResultGroupModel.builder()
               .id(resultGroup.getId())
               .title(resultGroup.getTitle())
               .description(resultGroup.getDescription())
               .cards(resultGroup.getCards()
                       .stream()
                       .map(CardModel::convertToCardModel)
                       .collect(Collectors.toList()))
               .build();
   }
}
