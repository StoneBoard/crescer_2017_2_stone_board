package br.com.crescer.stone_board.service;

import br.com.crescer.stone_board.entity.Card;
import br.com.crescer.stone_board.entity.Note;
import br.com.crescer.stone_board.entity.Person;
import br.com.crescer.stone_board.entity.model.NoteModel;
import br.com.crescer.stone_board.repository.CardRepository;
import br.com.crescer.stone_board.repository.NoteRepository;
import br.com.crescer.stone_board.repository.PersonRepository;
import br.com.crescer.stone_board.utils.BadRequestException;;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author willian
 */
@Service
public class NoteService {

    @Autowired
    NoteRepository noteRepository;
    @Autowired
    CardRepository cardRepository;
    @Autowired
    PersonRepository personRepository;

    public Note findById(Long id) {
        return noteRepository.findOne(id);
    }

    public void save(NoteModel noteModel, Person person) {
        Note note = noteModel.convertToNote(noteModel, person);
        Card card = cardRepository.findOne(noteModel.getId_card());
        card.getNotes().add(note);
        cardRepository.save(card);
    }

    public void update(NoteModel noteModel,Person personLogged) {
        if (personLogged.getId() != noteModel.getId_writer()) {
            throw new BadRequestException("Apenas o usuário que criou o comentario pode realizar esta ação.");
        }
        Note note = noteRepository.findOne(noteModel.getId());
        note.setText(noteModel.getText());
        noteRepository.save(note);
    }

    public void delete(Long idNote, Long idCard) {
        Card card = cardRepository.findOne(idCard);
        card.getNotes().removeIf(note -> note.getId() == idNote);
        cardRepository.save(card);
    }
}

