package br.com.crescer.stone_board.service;

import br.com.crescer.stone_board.entity.Board;
import br.com.crescer.stone_board.entity.Person;
import br.com.crescer.stone_board.entity.model.BoardMemberModel;
import br.com.crescer.stone_board.entity.model.BoardModel;
import br.com.crescer.stone_board.entity.model.BoardRegisterModel;
import br.com.crescer.stone_board.repository.BoardRepository;
import br.com.crescer.stone_board.repository.BoardSessionRepository;
import br.com.crescer.stone_board.repository.PersonRepository;
import br.com.crescer.stone_board.utils.BadRequestException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

/**
 *
 * @author Marcele Dorneles
 */
@Service
public class BoardService {

    @Autowired
    BoardRepository boardRepository;

    @Autowired
    PersonService personService;

    @Autowired
    PersonRepository personRepository;

    @Autowired
    BoardSessionRepository boardSessionRepository;

    public Long save(BoardRegisterModel boardRegister, Person personLogged) {
        boardRegister.setDeadline(boardRegister.getDeadline().with(LocalTime.MIN));
        if (!boardRegister.getDeadline().isAfter(LocalDateTime.now())) {
            throw new BadRequestException("A Data deve ser maior que o dia atual");
        }
        Board board = BoardRegisterModel.convertToBoard(boardRegister);

        if (CollectionUtils.isEmpty(boardRegister.getSessions())) {
            throw new BadRequestException("O Board deve possuir ao menos uma Sessão");
        }

        personLogged.getMyBoards().add(board);

        personRepository.save(personLogged);

        return board.getId();
    }

    public Board findById(Long id) {
        return boardRepository.findOne(id);
    }

    public List<Board> findAllBoards() {
        return boardRepository.findAll();
    }

    public void addMembers(BoardMemberModel boardMemberModel, Person personLogged) {
        Person person = personRepository.findOne(boardMemberModel.getId_person());
        if (personLogged.getId() == person.getId()) {
            throw new BadRequestException("Usuário não pode ser adicionado");
        }
        Board board = boardRepository.findOne(boardMemberModel.getId_board());
        if (person.getConnectBoards().stream().anyMatch(b -> b.getId() == board.getId())) {
            throw new BadRequestException("Usuário não pode ser adicionado");
        }
        person.getConnectBoards().add(board);
        personRepository.save(person);
    }

    public BoardModel update(BoardRegisterModel boardRegister) {
        Board board = boardRepository.findOne(boardRegister.getId());

        board.setTitle(boardRegister.getTitle());
        board.setDeadline(boardRegister.getDeadline());
        boardRepository.save(board);
        return BoardModel.convertToBoardModel(board);

    }

    public boolean userAuthenticadedBoard(Long id, Person personLogged) {
        Board board = findById(id);
        return board.getMembers().stream().anyMatch(x -> Objects.equals(x.getId(), personLogged.getId()))
                || personLogged.getMyBoards().stream().anyMatch(x -> Objects.equals(x.getId(), id));

    }

}
