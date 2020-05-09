package orpg.com.pokemonorpg.entities.trainer;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.NaturalId;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.ElementCollection;
import javax.persistence.Embeddable;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Embeddable
@Getter @Setter
@NoArgsConstructor
public class UserDetailsImpl implements UserDetails {
    //UserDetail Settings
    @NaturalId
    protected String username;
    protected String password;
    protected boolean isAccountNonExpired = true;
    protected boolean isAccountNonLocked = true;
    protected boolean isCredentialsNonExpired = true;
    protected boolean isEnabled = true;
    @Setter(value = AccessLevel.NONE)
    @ElementCollection
    protected Set<SimpleGrantedAuthority> roles = new HashSet<>();

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles;
    }

    public void addAuthority(Roles role) {
        roles.add(new SimpleGrantedAuthority(role.toString()));
    }

    @Override
    public boolean isAccountNonExpired() {
        return isAccountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return isAccountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return isCredentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return isEnabled;
    }
}
