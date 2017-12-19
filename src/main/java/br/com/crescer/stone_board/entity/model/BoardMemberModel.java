package br.com.crescer.stone_board.entity.model;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author marcele.dorneles
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BoardMemberModel implements Serializable {

    private Long id_board;
    private Long id_person;
}
