package br.com.crescer.stone_board.controller;

import br.com.crescer.stone_board.entity.model.NoteModel;
import br.com.crescer.stone_board.service.NoteService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Marcele Dorneles
 */
@RestController
@RequestMapping("/note")
public class NoteController {
    @Autowired
    private NoteService noteService;
    
    @PostMapping
    public void Save(@Validated @RequestBody NoteModel noteModel) {
       noteService.save(noteModel);
    }
    
    @GetMapping("/findByCard/{idCard}")
    public List<NoteModel> findByBoardId(@PathVariable Long idCard){
       return noteService.findByCardId(idCard);
    }
    
    @PutMapping
    public void update(@RequestBody NoteModel noteModel){
      noteService.update(noteModel);
    }
    
    @DeleteMapping("{idNote}")
    public void delete(@PathVariable Long idNote){
        noteService.delete(idNote);
    }
    
}
