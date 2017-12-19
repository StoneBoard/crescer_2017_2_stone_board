package br.com.crescer.stone_board.entity.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author Marcele Dorneles
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MessageErrorModel {

    public String menssage;
    public String type;
}
