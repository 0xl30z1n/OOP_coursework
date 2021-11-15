package br.iesb.imarket.repository;

import br.iesb.imarket.model.Card;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CardRepo extends JpaRepository<Card,Long> {
}
