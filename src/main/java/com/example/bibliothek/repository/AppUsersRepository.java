package com.example.bibliothek.repository;

import com.example.bibliothek.entity.AppUsers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Repository
@Transactional(readOnly = true)
public interface AppUsersRepository extends JpaRepository<AppUsers, UUID> {

}
