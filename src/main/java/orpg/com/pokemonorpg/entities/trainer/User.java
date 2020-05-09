package orpg.com.pokemonorpg.entities.trainer;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import orpg.com.pokemonorpg.entities.Gender;
import orpg.com.pokemonorpg.entities.Image;
import orpg.com.pokemonorpg.entities.pokemon.Pokemon;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Setter @Getter
@NoArgsConstructor
@Cacheable
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class User extends Base {
    @Version
    private int version;

    //Pokemon Settings
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @Setter(value = AccessLevel.NONE)
    private Set<Pokemon> pokemon = new HashSet<>();
    @Setter(value = AccessLevel.NONE)
    private int maxPokemon = 500;

    //UserDetails Settings
    @Embedded
    private UserDetailsImpl userDetails = new UserDetailsImpl();

    private User(String username,
                 String password,
                 Set<SimpleGrantedAuthority> roles,
                 LocalDate dob,
                 Gender gender,
                 Image icon) {
        this.userDetails.username = username;
        this.userDetails.password = password;
        this.userDetails.roles = roles;
        this.setDob(dob);
        this.setGender(gender);
        this.setIcon(icon);
    }

    public void addPokemon(Pokemon p) {
        if (pokemon.size() >= maxPokemon) {
            throw new RuntimeException("You can't catch anymore pokemon!");
        }
        pokemon.add(p);
        p.setUser(this);
    }

    public void removePokemon(Pokemon p) {
        pokemon.remove(p);
        p.setUser(null);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return userDetails.username != null && userDetails.username.equals(user.userDetails.username);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userDetails.username);
    }

    public static class Factory {
        public static User createUser(String username,
                                      String password,
                                      Gender gender,
                                      LocalDate dob,
                                      Roles role,
                                      Image icon) {
            return new User(
                    username,
                    password,
                    createRoles(role),
                    dob,
                    gender,
                    icon);
        }

        private static Set<SimpleGrantedAuthority> createRoles(Roles role) {
            HashSet<SimpleGrantedAuthority> roles = new HashSet<>();
            if (role == Roles.ADMINISTRATOR) {
                roles.add(new SimpleGrantedAuthority(Roles.ADMINISTRATOR.toString()));
            }
            if (role == Roles.ADMINISTRATOR || role == Roles.MODERATOR) {
                roles.add(new SimpleGrantedAuthority(Roles.MODERATOR.toString()));
            }
            roles.add(new SimpleGrantedAuthority(Roles.USER.toString()));
            return roles;
        }
    }
}
