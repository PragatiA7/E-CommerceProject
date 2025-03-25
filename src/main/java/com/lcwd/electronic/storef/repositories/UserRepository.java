package com.lcwd.electronic.storef.repositories;

import com.lcwd.electronic.storef.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User,String> {

   Optional<User> findByEmail(String email);

   Optional<User> findByEmailAndPassword(String email,String password);
    List<User> findByNameContaining(String keyword);
}
