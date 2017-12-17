package br.com.crescer.stone_board.entity.model;

import br.com.crescer.stone_board.entity.Vote;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author Júlia
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class VoteModel {

    private boolean positive;
    private Long id_person;
    private Long id_card;

    public static VoteModel convertToVoteModel(Vote vote) {
        return VoteModel.builder()
                .positive(vote.isPositive())
                .id_person(vote.getPerson().getId())
                .build();
    }
}
