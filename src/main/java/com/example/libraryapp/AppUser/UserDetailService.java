package com.example.libraryapp.AppUser;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailService implements UserDetailsService {
    @Autowired
    UserRepository userRepository;

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
       LibraryUser libraryUser = this.userRepository.findByUserName(username);
        if (libraryUser == null)
            throw new UsernameNotFoundException("User not found");
        return new com.example.libraryapp.AppUser.Details(libraryUser);
    }
}
