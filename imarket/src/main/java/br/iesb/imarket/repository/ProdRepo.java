package br.iesb.imarket.repository;

import br.iesb.imarket.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProdRepo extends JpaRepository<Product, Long> {
    Optional<List<Product>> findByCategoryContains(String category);
    Optional<List<Product>> findByBrandContaining(String brand);
    Optional<List<Product>> getByPromotionIsTrue();
    Optional<Product> findByNameContaining(String name);
}
