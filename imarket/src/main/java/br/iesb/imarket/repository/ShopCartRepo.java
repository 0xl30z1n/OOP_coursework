package br.iesb.imarket.repository;

import br.iesb.imarket.model.ShopCart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShopCartRepo extends JpaRepository<ShopCart,Long> {
}
