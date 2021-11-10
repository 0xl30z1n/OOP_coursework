package br.iesb.imarket.repository;

import br.iesb.imarket.model.ProductEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProdRepository extends CrudRepository<ProductEntity, Long> {
    Optional<List<ProductEntity>> findByCategoryContaining(String category);
    Optional<List<ProductEntity>> findByBrandContaining(String brand);
    Optional<List<ProductEntity>> getByPromotionIsTrue();
}
