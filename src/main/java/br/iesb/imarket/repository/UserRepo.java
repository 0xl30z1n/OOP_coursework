package br.iesb.imarket.repository;

import br.iesb.imarket.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepo extends JpaRepository<User, Long> {
    Optional<User> findByEmailAndPasswordContaining(String email, String password);
    Optional<List<User>> findByEmailOrPasswordContaining(String email, String password);
}
