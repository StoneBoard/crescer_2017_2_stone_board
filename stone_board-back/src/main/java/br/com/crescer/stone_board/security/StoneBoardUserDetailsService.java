package br.com.crescer.stone_board.security;

import br.com.crescer.stone_board.entity.Person;
import br.com.crescer.stone_board.service.PersonService;
import java.util.ArrayList;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
/**
 *
 * @author willian
 */

@Service
public class StoneBoardUserDetailsService implements UserDetailsService {

    @Autowired
    private PersonService personService;

    PasswordEncoder encoder = new BCryptPasswordEncoder();

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        final Person person = personService.findByEmail(email);
        return Optional
                .ofNullable(new User(person.getEmail(), encoder.encode("1234"), new ArrayList<>()))
//                .ofNullable(new User(person.getEmail(), person.getPass(), new ArrayList<>()))
                .orElse(null);
    }
}