package br.com.crescer.stone_board.security;

import br.com.crescer.stone_board.entity.Person;
import br.com.crescer.stone_board.service.PersonService;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
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
 * @author carloshenrique
 */
@Service
public class SocialUserDetailsService implements UserDetailsService {

    @Autowired
    private PersonService personService;

    PasswordEncoder encoder = new BCryptPasswordEncoder();

        public static class CustomUserDetails extends User {
    @Getter
    @Setter
    private String nome;

    public CustomUserDetails(Person person, Collection<? extends GrantedAuthority> authorities
    ) {
        super(person.getEmail(), person.getPass(), authorities);
        this.nome = person.getFullName();
        }
    }

@Override
        public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        final Person person = personService.findByEmail(username);
	if (person == null) {
            return null;
	}
        
        final List<GrantedAuthority> grants = new ArrayList<>();
        grants.add(() -> "ROLE_ADMIN");
        return new CustomUserDetails(person, grants);

    }
}