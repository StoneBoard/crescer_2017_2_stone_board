package br.com.crescer.stone_board.entity.model;

import br.com.crescer.stone_board.entity.Board;
import br.com.crescer.stone_board.entity.Card;
import br.com.crescer.stone_board.entity.Person;
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
public class CardModel implements Serializable{
    private Long id;
    @NotNull(message = "error.text.notnull")
    @Size(max = 300, message = "error.text.size")
    private String text;
    private Long id_writer;
   // @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    private LocalDateTime creationDate;
    private Long id_session;
    private List<VoteModel> votes;
    private List<NoteModel> notes;
    
    public static Card convertToCard(CardModel cardModel, Person writer) {
       return Card.builder()
               .id(cardModel.getId())
               .text(cardModel.getText())
               .writer(writer)
               .creationDate(LocalDateTime.now())
               .build();
   }
    
   public static CardModel convertToCardModel(Card card) {
       return CardModel.builder()
               .id(card.getId())
               .text(card.getText())
               .id_writer(card.getWriter().getId())
               .creationDate(card.getCreationDate())
               .votes(card.getVotes()
                        .stream()
                        .map(VoteModel::convertToVoteModel)
                        .collect(Collectors.toList()))
               .notes(card.getNotes()
                        .stream()
                        .map(NoteModel::convertToNoteModel)
                        .collect(Collectors.toList()))
               .build();
   } 
}
