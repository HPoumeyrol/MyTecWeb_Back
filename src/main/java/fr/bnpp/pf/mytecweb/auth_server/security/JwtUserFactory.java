package fr.bnpp.pf.mytecweb.auth_server.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import fr.bnpp.pf.mytecweb.auth_server.model.security.Authority;
import fr.bnpp.pf.mytecweb.auth_server.model.security.User;
import fr.bnpp.pf.mytecweb.rest.models.UserAccount;

import java.util.List;
import java.util.stream.Collectors;

public final class JwtUserFactory {

    private JwtUserFactory() {
    }

    public static JwtUser create(User user) {
        return new JwtUser(
                user.getId(),
                user.getUsername(),
                user.getFirstname(),
                user.getLastname(),
                user.getEmail(),
                user.getPassword(),
                mapToGrantedAuthorities(user.getAuthorities()),
                user.getEnabled(),
                user.getLastPasswordResetDate()
        );
    }
    
    public static JwtUser create(UserAccount userAccount) {
        return new JwtUser(
        		userAccount.getId(),
        		userAccount.getUid(),
        		userAccount.getFirstName(),
        		userAccount.getLastName(),
        		userAccount.getEmail(),
        		userAccount.getPassword(),
             null, //mapToGrantedAuthorities(user.getAuthorities()),
             userAccount.isEnabled(),
             userAccount.getLastPasswordResetDate()
        );
    }

    
    private static List<GrantedAuthority> mapToGrantedAuthorities(List<Authority> authorities) {
        return authorities.stream()
                .map(authority -> new SimpleGrantedAuthority(authority.getName().name()))
                .collect(Collectors.toList());
    }
}
