package com.libreria.repositories;

import com.libreria.entities.User;
import com.libreria.enums.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findFirstByEmail(String email);


    User findByUserRole(UserRole userRole);
}
