package br.com.crescer.stone_board.entity.model;

import br.com.crescer.stone_board.entity.Person;
import java.io.Serializable;
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
public class PersonModel implements Serializable{
    @NotNull(message = "error.fullName.notnull")
    @Size(max= 255, message = "error.fullName.size")
    private String fullName;
    @NotNull(message = "error.email.notnull")
    @Size(max= 255, message = "error.email.size")
    private String email;
    @NotNull(message = "error.pass.notnull")
    @Size(max= 128, message = "error.pass.size")
    private String pass;
    
    public static Person convertToPerson(PersonModel personModel) {
       return Person.builder()
               .fullName(personModel.getFullName())
               .email(personModel.getEmail())
               .pass(personModel.getPass())
               .build();
   }
    public static PersonModel convertToPersonModel(Person person){
        return PersonModel.builder()
                .fullName(person.getFullName())
                .email(person.getEmail())
                .build();
    }
}
