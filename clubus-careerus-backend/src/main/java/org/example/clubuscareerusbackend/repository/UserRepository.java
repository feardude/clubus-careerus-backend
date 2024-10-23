package org.example.clubuscareerusbackend.repository;

import org.example.clubuscareerusbackend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {


    Optional<User> findByEmail(String email);

    Optional<User> findByLogin(String login);

    Boolean existsByLogin(String login);

    Boolean existsByEmail(String email);
}
