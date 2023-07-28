package cl.awakelab.SprintModulo6.web.security;

import cl.awakelab.SprintModulo6.model.persistence.entity.Usuario;
import cl.awakelab.SprintModulo6.model.persistence.repository.LoginRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

public class UserDetailsServiceImpl implements UserDetailsService {
    private final LoginRepository repository;
    public UserDetailsServiceImpl(LoginRepository repository){
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Usuario> user = repository.findByUsername(username);
        if (user.isPresent()){
            return new RCUserDetails(user.get());
        }

        throw new UsernameNotFoundException("El usuario no existe!");
    }
}

