package br.iesb.imarket.service;

import br.iesb.imarket.dto.ProductDTO;
import br.iesb.imarket.model.ProductEntity;
import br.iesb.imarket.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

@Service
public class ProductService {

    private ProductRepository productRepo = new ProductRepository();

    public List<ProductDTO> getProduct(){
        List<ProductDTO> listProducts = new ArrayList<>();

        List<ProductEntity> aux = productRepo.getProducts();
        for(ProductEntity tmp: aux){
            ProductDTO dto = new ProductDTO();
            dto.setName(tmp.getName());
            dto.setBrand(tmp.getBrand());
            dto.setQuantity(tmp.getQuantity());
            dto.setPromotion(tmp.isPromotion());
            dto.setPrice(tmp.getPrice());
            dto.setDescription(tmp.getDescription());
            dto.setPercent(tmp.getPercent());
            dto.setCategory(tmp.getCategory());

            listProducts.add(dto);
        }
        return listProducts;
    }
    public void dto_entity(ProductDTO product){
        ProductEntity aux = new ProductEntity();

        aux.setName(product.getName());
        aux.setBrand(product.getBrand());
        aux.setQuantity(product.getQuantity());
        aux.setPromotion(product.isPromotion());
        aux.setPrice(product.getPrice());
        aux.setDescription(product.getDescription());
        aux.setPercent(product.getPercent());
        aux.setCategory(product.getCategory());
        Date data = new Date();
        aux.setCreate(data);

        productRepo.saveProduct(aux);
    }
    public int checkId(long id){
        List<ProductEntity> aux = productRepo.getProducts();
        for(ProductEntity tmp : aux){
            if(tmp.getId() == id){
                return 1;
            }
        }
        return 0;
    }
    public int serviceDel(long id){
        return productRepo.deleteProduct(id);
    }
    public void serviceDelCategory(String category){
        productRepo.deleteProductCategory(category);
    }
    public void serviceDelBrand(String brand){
        productRepo.deleteProductBrand(brand);
    }
    public void serviceDelAll(){
        productRepo.deleteAll();
    }
    public boolean dto_entity_semID(long id, ProductDTO product){
        ProductEntity aux = new ProductEntity();

        aux.setName(product.getName());
        aux.setBrand(product.getBrand());
        aux.setQuantity(product.getQuantity());
        aux.setPromotion(product.isPromotion());
        aux.setPrice(product.getPrice());
        aux.setDescription(product.getDescription());
        aux.setPercent(product.getPercent());
        aux.setCategory(product.getCategory());

        return productRepo.updateProduct(id,aux);
    }

    public void updatePromotionCategory(String category, float percent){
        productRepo.updatePromotionProductCategory(category,percent);
    }

    public void updatePromotionBrand(String Brand, float percent){
        productRepo.updatePromotionProductBrand(Brand,percent);
    }

    public void updatePromotionAll(float percent){
        productRepo.updatePromotionProduct(percent);
    }

    public List<ProductDTO> getProductCategory(String category){
        List<ProductDTO> listProducts = new ArrayList<>();

        List<ProductEntity> aux = productRepo.getProductsCategory(category);
        for(ProductEntity tmp: aux){
            ProductDTO dto = new ProductDTO();
            dto.setName(tmp.getName());
            dto.setBrand(tmp.getBrand());
            dto.setQuantity(tmp.getQuantity());
            dto.setPromotion(tmp.isPromotion());
            dto.setPrice(tmp.getPrice());
            dto.setDescription(tmp.getDescription());
            dto.setPercent(tmp.getPercent());
            dto.setCategory(tmp.getCategory());

            listProducts.add(dto);
        }
        return listProducts;
    }

    public List<ProductDTO> getProductBrand(String brand){
        List<ProductDTO> listProducts = new ArrayList<>();

        List<ProductEntity> aux = productRepo.getProductsBrand(brand);
        for(ProductEntity tmp: aux){
            ProductDTO dto = new ProductDTO();
            dto.setName(tmp.getName());
            dto.setBrand(tmp.getBrand());
            dto.setQuantity(tmp.getQuantity());
            dto.setPromotion(tmp.isPromotion());
            dto.setPrice(tmp.getPrice());
            dto.setDescription(tmp.getDescription());
            dto.setPercent(tmp.getPercent());
            dto.setCategory(tmp.getCategory());

            listProducts.add(dto);
        }
        return listProducts;
    }

    public List<ProductDTO> getProductsPromotion(){
        List<ProductDTO> listProducts = new ArrayList<>();

        List<ProductEntity> aux = productRepo.getProductsPromotion();
        for(ProductEntity tmp: aux){
            ProductDTO dto = new ProductDTO();
            dto.setName(tmp.getName());
            dto.setBrand(tmp.getBrand());
            dto.setQuantity(tmp.getQuantity());
            dto.setPromotion(tmp.isPromotion());
            dto.setPrice(tmp.getPrice());
            dto.setDescription(tmp.getDescription());
            dto.setPercent(tmp.getPercent());
            dto.setCategory(tmp.getCategory());

            listProducts.add(dto);
        }
        return listProducts;
    }

    public List<ProductDTO> getProductsCrescente(){
        List<ProductDTO> listProducts = new ArrayList<>();

        List<ProductEntity> aux = productRepo.getProductsCrescente();
        for(ProductEntity tmp: aux){
            ProductDTO dto = new ProductDTO();
            dto.setName(tmp.getName());
            dto.setBrand(tmp.getBrand());
            dto.setQuantity(tmp.getQuantity());
            dto.setPromotion(tmp.isPromotion());
            dto.setPrice(tmp.getPrice());
            dto.setDescription(tmp.getDescription());
            dto.setPercent(tmp.getPercent());
            dto.setCategory(tmp.getCategory());

            listProducts.add(dto);
        }
        return listProducts;
    }
}
