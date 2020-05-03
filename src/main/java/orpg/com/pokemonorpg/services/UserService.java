package orpg.com.pokemonorpg.services;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import orpg.com.pokemonorpg.entities.trainer.User;
import orpg.com.pokemonorpg.repositories.UserRepository;

import java.util.Optional;

@Service
public class UserService implements UserDetailsService {
    private final UserRepository repository;

    public UserService(UserRepository repository) {
        this.repository = repository;
    }
    @Transactional
    public User findUserByUsername(String username) {
        return repository.findUserByUsername(username).orElseThrow(() -> new RuntimeException("User was not found!"));
    }
    @Transactional
    public User findUserById(Long id) {
        Optional<User> user = repository.findUserById(id);
        return user.orElseThrow(() -> new RuntimeException("User was not found!"));
    }
    @Override
    @Transactional
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return findUserByUsername(s);
    }
    @Transactional
    public User save(User user) {
        return repository.save(user);
    }
}
