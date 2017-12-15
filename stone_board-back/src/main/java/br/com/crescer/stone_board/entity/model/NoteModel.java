package br.com.crescer.stone_board.entity.model;

import br.com.crescer.stone_board.entity.Note;
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
public class NoteModel implements Serializable{
    
    private long id;
    private long id_writer;
    @NotNull(message = "error.text.notnull")
    @Size(max = 200, message = "error.text.icon.size")
    private String text;
    private Long id_card;
    
    public static Note convertToNote(NoteModel noteModel, Person writer) {
        return Note.builder()
                .writer(writer)
                .text(noteModel.getText())
                .build();
    }
    
    public static NoteModel convertToNoteModel(Note note) {
        return NoteModel.builder()
                .id(note.getId())
                .id_writer(note.getWriter().getId())
                .text(note.getText())
                .build();
    }
}
