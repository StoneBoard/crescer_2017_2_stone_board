package br.com.crescer.stone_board.entity.model;

import br.com.crescer.stone_board.entity.Board;
import br.com.crescer.stone_board.entity.Card;
import br.com.crescer.stone_board.entity.Person;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
import java.time.LocalDateTime;
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
public class CardModel implements Serializable{
    @NotNull(message = "error.text.notnull")
    @Size(max = 300, message = "error.text.size")
    private String text;
    private Long id_writer;
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    private LocalDateTime creationDate;
    
    public static Card convertToCard(CardModel cardModel, Person writer) {
       return Card.builder()
               .text(cardModel.getText())
               .writer(writer)
               .creationDate(cardModel.getCreationDate())
               .build();
   }
}
