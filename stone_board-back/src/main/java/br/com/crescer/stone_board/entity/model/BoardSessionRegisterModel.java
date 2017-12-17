package br.com.crescer.stone_board.entity.model;

import br.com.crescer.stone_board.entity.BoardSession;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author Júlia
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BoardSessionRegisterModel {

    @NotNull(message = "Título não pode ser nulo")
    @Size(min = 1, max = 128, message = "Título não pode possuir mais de 128 carácteres ou menos de 1 caracter")
    private String title;
    @Min(value = 0)
    @Max(value = 3)
    @NotNull(message = "A cor não pode ser nula")
    private int color;
    @Size(max = 512, message = "Icone não pode possuir mais de 512 caracteres")
    private String icon;

    public static BoardSession convertToBoardSession(BoardSessionRegisterModel boardSessionRegister) {
        return BoardSession.builder()
                .title(boardSessionRegister.getTitle())
                .color(boardSessionRegister.getColor())
                .icon(boardSessionRegister.getIcon())
                .build();
    }
}
