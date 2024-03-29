package com.example.bibliothek.appUser;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
@Transactional(readOnly = true)
public interface AppUserRepository extends
        JpaRepository<AppUser, Long> {

    //@Query("SELECT a.email FROM AppUser a WHERE a.email= :email")
    Optional<AppUser> findByEmail(String email);

    @Transactional
    @Modifying
    @Query("UPDATE AppUser a " +
            "SET a.enabled = TRUE WHERE a.email = ?1")
    int enableAppUser(String email);

    AppUser findAppUserByUserId(Long userId);

    @Query("SELECT a FROM AppUser a WHERE a.userName = :username")
    AppUser findAppUserByUserName(@Param("username") String userName);



}

