package br.com.crescer.stone_board.entity.model;

import br.com.crescer.stone_board.entity.Note;
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
public class NoteModel implements Serializable{
    
    private long id_writer;
    @NotNull(message = "error.text.notnull")
    @Size(max = 200, message = "error.text.icon.size")
    private String text;
    
     public static Note convertToNote(NoteModel noteModel, Person writer) {
       return Note.builder()
               .writer(writer)
               .text(noteModel.getText())
               .build();
   }
}
