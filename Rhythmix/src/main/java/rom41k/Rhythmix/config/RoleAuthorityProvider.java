package rom41k.Rhythmix.config;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import rom41k.Rhythmix.database.enums.Role;

import java.util.Collection;
import java.util.List;

public class RoleAuthorityProvider {

    public static Collection<? extends GrantedAuthority> getAuthorities(Role role) {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }
}