package br.com.crescer.stone_board.service;

import br.com.crescer.stone_board.entity.Board;
import br.com.crescer.stone_board.entity.Person;
import br.com.crescer.stone_board.entity.model.PersonModel;
import br.com.crescer.stone_board.repository.PersonRepository;
import br.com.crescer.stone_board.utils.PersonComponent;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 *
 * @author Júlia
 */
@Service
public class PersonService {

    @Autowired
    PersonRepository personRepository;
    @Autowired
    PersonComponent personComponent;

    private static PasswordEncoder encoder = new BCryptPasswordEncoder();

    public Person findByEmail(String email) {
        return personRepository.findByEmail(email);
    }

    public Person createAcount(PersonModel personModel) {
        Person person = PersonModel.convertToPerson(personModel);
        person.setPass(encoder.encode(person.getPass()));
        return personRepository.save(person);
    }

    public List<Board> listMyBoards() {
        return personComponent.loggedPersonDetails().getMyBoards();
    }

    public List<Board> listConnectBoards() {
        return personComponent.loggedPersonDetails().getConnectBoards();
    }

    public List<PersonModel> findPersonsByEmail(String email) {
        return personRepository.findByEmailContainingIgnoreCase(email)
                .stream()
                .map(PersonModel::convertToPersonModel)
                .collect(Collectors.toList());
    }
}
