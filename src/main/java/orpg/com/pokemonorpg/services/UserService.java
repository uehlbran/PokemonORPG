package orpg.com.pokemonorpg.services;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import orpg.com.pokemonorpg.entities.trainer.User;
import orpg.com.pokemonorpg.repositories.UserRepository;

@Service
public class UserService implements UserDetailsService {
    private final UserRepository repository;

    public UserService(UserRepository repository) {
        this.repository = repository;
    }
    @Transactional
    public User findUserByUsername(String username) throws UsernameNotFoundException {
        return repository.findUserByUserDetailsUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User was not found!"));
    }
    @Override
    @Transactional
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return findUserByUsername(s).getUserDetails();
    }
    @Transactional
    public User save(User user) {
        return repository.save(user);
    }
}
