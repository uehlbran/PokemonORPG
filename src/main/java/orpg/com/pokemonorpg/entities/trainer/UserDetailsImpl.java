package orpg.com.pokemonorpg.entities.trainer;

import lombok.*;
import org.hibernate.annotations.NaturalId;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.ElementCollection;
import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Embeddable
@Getter
@NoArgsConstructor @AllArgsConstructor
public class UserDetailsImpl implements UserDetails {
    @NaturalId
    protected String username;
    protected String password;
    protected boolean isAccountNonExpired = true;
    protected boolean isAccountNonLocked = true;
    protected boolean isCredentialsNonExpired = true;
    protected boolean isEnabled = true;
    @Setter(value = AccessLevel.NONE)
    @ElementCollection(fetch = FetchType.EAGER)
    protected Set<GrantedAuthority> roles = new HashSet<>();

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles;
    }

    public void addAuthority(Roles role) {
        roles.add(new SimpleGrantedAuthority(role.toString()));
    }
}
