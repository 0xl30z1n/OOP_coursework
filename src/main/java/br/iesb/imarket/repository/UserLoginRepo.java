package br.iesb.imarket.repository;

import br.iesb.imarket.model.UserLogin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserLoginRepo extends JpaRepository<UserLogin, Long> {
}
