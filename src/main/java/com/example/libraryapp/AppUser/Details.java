package com.example.libraryapp.AppUser;

import java.util.Collection;
import java.util.Collections;

import com.example.libraryapp.AppUser.LibraryUser;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component
public class Details implements UserDetails {
    private LibraryUser user;

    public Details(LibraryUser user) {
        this.user = user;
    }

    public Collection<? extends GrantedAuthority> getAuthorities() {
        return (Collection)Collections.singleton(new SimpleGrantedAuthority(this.user.getRole()));
    }

    public String getPassword() {
        return this.user.getPassword();
    }

    public String getUsername() {
        return this.user.getUsername();
    }

    public boolean isAccountNonExpired() {
        return true;
    }

    public boolean isAccountNonLocked() {
        return true;
    }

    public boolean isCredentialsNonExpired() {
        return true;
    }

    public boolean isEnabled() {
        return true;
    }
}
