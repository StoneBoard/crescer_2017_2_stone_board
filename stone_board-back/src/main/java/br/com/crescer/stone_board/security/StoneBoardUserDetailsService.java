package br.com.crescer.stone_board.security;

import br.com.crescer.stone_board.entity.Person;
import br.com.crescer.stone_board.service.PersonService;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
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

    public static class CustomUserDetails extends User {

        @Getter
        @Setter
        private String fullName;

        public CustomUserDetails(Person person, Collection<? extends GrantedAuthority> authorities) {
            super(person.getEmail(), person.getPass(), authorities);
            this.fullName = person.getFullName();

        }
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        final Person person = personService.findByEmail(email);
        return Optional
                .ofNullable(new User(person.getEmail(), encoder.encode("1234"), new ArrayList<>()))
                //   .ofNullable(new CustomUserDetails(person, new ArrayList<>()))
                .orElse(null);
    }
}
