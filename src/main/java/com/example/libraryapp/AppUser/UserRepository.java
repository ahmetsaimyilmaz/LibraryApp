package com.example.libraryapp.AppUser;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<LibraryUser, Integer> {
    LibraryUser findByUserName(String paramString);
}
