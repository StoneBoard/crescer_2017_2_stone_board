package br.com.crescer.stone_board.entity.model;

import br.com.crescer.stone_board.entity.Person;
import java.io.Serializable;
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
public class PersonModel implements Serializable{
    @NotNull(message = "erro.fullName.notnull")
    @Size(max= 255, message = "erro.fullName.size")
    private String fullName;
    @NotNull(message = "erro.email.notnull")
    @Size(max= 255, message = "erro.email.size")
    private String email;
    @NotNull(message = "erro.pass.notnull")
    @Size(max= 128, message = "erro.pass.size")
    private String pass;
    
    public static Person convertToPerson(PersonModel personModel) {
       return Person.builder()
               .fullName(personModel.getFullName())
               .email(personModel.getEmail())
               .pass(personModel.getPass())
               .build();
   }
}
