package br.com.crescer.stone_board.utils;

import br.com.crescer.stone_board.entity.Person;
import br.com.crescer.stone_board.entity.model.LoggedPersonModel;
import br.com.crescer.stone_board.service.PersonService;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

@Component
public class PersonComponent {

    @Autowired
    PersonService personService;
    
    public LoggedPersonModel loggedPerson() {
        return Optional
                .ofNullable(loggedPersonDetails())
                .map(LoggedPersonModel::convertToLoggedPerson)
                .orElse(null);
    }

    public Person loggedPersonDetails() {
        return Optional
                .ofNullable(user())
                .map(User::getUsername)
                .map(personService::findByEmail)
                .orElse(null);
    }

    private User user() {
        return Optional
                .ofNullable(SecurityContextHolder.getContext().getAuthentication())
                .map(Authentication::getPrincipal)
                .map(User.class::cast)
                .orElse(null);
    }
    
    
    
}
