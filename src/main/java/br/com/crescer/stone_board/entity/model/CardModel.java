package br.com.crescer.stone_board.entity.model;

import br.com.crescer.stone_board.entity.Card;
import br.com.crescer.stone_board.entity.Person;
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
public class CardModel implements Serializable {

    private Long id;
    @NotNull(message = "O texto não pode ser nulo.")
    @Size(max = 300, min = 1, message = "O texto não pode ultrapassar 300 caracteres.")
    private String text;
    private Long id_writer;
    // @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    private LocalDateTime creationDate;
    @NotNull(message = "Cor deve ser informada.")
    private int color;
    @NotNull(message = "Id da Session não pode ser nulo.")
    private Long id_session;
    private List<VoteModel> votes;
    private List<NoteModel> notes;

    public static Card convertToCard(CardModel cardModel, Person writer) {
        return Card.builder()
                .id(cardModel.getId())
                .text(cardModel.getText())
                .writer(writer)
                .creationDate(LocalDateTime.now())
                .color(cardModel.getColor())
                .build();
    }

    public static CardModel convertToCardModel(Card card, Long idSession) {
        return CardModel.builder()
                .id(card.getId())
                .text(card.getText())
                .id_writer(card.getWriter().getId())
                .creationDate(card.getCreationDate())
                .color(card.getColor())
                .id_session(idSession)
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

    public static CardModel convertToCardModel(Card card) {
        return CardModel.builder()
                .id(card.getId())
                .text(card.getText())
                .id_writer(card.getWriter().getId())
                .creationDate(card.getCreationDate())
                .color(card.getColor())
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
