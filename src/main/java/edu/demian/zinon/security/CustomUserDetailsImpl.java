package edu.demian.zinon.security;

import com.fasterxml.jackson.annotation.JsonIgnore;
import edu.demian.zinon.entity.Status;
import edu.demian.zinon.entity.User;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

@Data
public class CustomUserDetailsImpl implements UserDetails {

    private Long id;
    private String email;
    private String username;
    @JsonIgnore
    private String password;
    private Collection<? extends GrantedAuthority> authorities;
    private Boolean isActive;

    private CustomUserDetailsImpl(Long id, String email, String username, String password, Collection<? extends GrantedAuthority> authorities, Boolean isActive) {
        this.id = id;
        this.email = email;
        this.username = username;
        this.password = password;
        this.authorities = authorities;
        this.isActive = isActive;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return isActive;
    }

    @Override
    public boolean isAccountNonLocked() {
        return isActive;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return isActive;
    }

    @Override
    public boolean isEnabled() {
        return isActive;
    }

    public static CustomUserDetailsImpl build(User user) {
        return new CustomUserDetailsImpl(
                user.getId(),
                user.getEmail(),
                user.getUsername(),
                user.getPassword(),
                user.getRole().getAuthorities(),
                user.getStatus() == Status.ACTIVE
        );
    }
}
