package br.iesb.imarket.service;

import br.iesb.imarket.dto.request.ProductDTO;
import br.iesb.imarket.exception.ProductNotFoundException;
import br.iesb.imarket.model.ProductEntity;
import br.iesb.imarket.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ProductService {

    private ProductRepository productRepo = new ProductRepository();
    private ProductDTO dto;
    private List<ProductDTO> listProducts;
    private ProductEntity aux;

    public List<ProductDTO> getProduct(){
        listProducts = new ArrayList<>();

        List<ProductEntity> aux = productRepo.getProducts();
        entity_dto(listProducts, aux);

        return listProducts;
    }

    public List<ProductDTO> getProductCategory(String category){
        listProducts = new ArrayList<>();

        List<ProductEntity> aux = productRepo.getProductsCategory(category);
        entity_dto(listProducts, aux);
        return listProducts;
    }

    public List<ProductDTO> getProductBrand(String brand){
        listProducts = new ArrayList<>();

        List<ProductEntity> aux = productRepo.getProductsBrand(brand);
        entity_dto(listProducts, aux);
        return listProducts;
    }

    public List<ProductDTO> getProductsPromotion(){
        listProducts = new ArrayList<>();

        List<ProductEntity> aux = productRepo.getProductsPromotion();
        entity_dto(listProducts, aux);
        return listProducts;
    }

    public List<ProductDTO> getProductsCrescente(){
        listProducts = new ArrayList<>();

        List<ProductEntity> aux = productRepo.getProductsCrescente();
        entity_dto(listProducts, aux);
        return listProducts;
    }

    public int saveProduct(ProductDTO product){
        aux = new ProductEntity();

        int checker = productRules(product);
        if (checker >= 1 && checker < 8)
            return checker;

        dto_entity(product, aux);
        Date data = new Date();
        aux.setCreate(data);

        productRepo.saveProduct(aux);
        return 0;
    }

    public int serviceDel(long id) throws ProductNotFoundException{
        if(id <= 0){
            return -1;
        }
        return productRepo.deleteProduct(id);
    }
    public int serviceDelCategory(String category){
        if(verifyCategory(category)){
            return -1;
        }
        return productRepo.deleteProductCategory(category);
    }
    public int serviceDelBrand(String brand){
        if(verifyBrand(brand)){
            return -1;
        }
        return productRepo.deleteProductBrand(brand);
    }
    public void serviceDelAll(){
        productRepo.deleteAll();
    }

    public int updateProduct(long id, ProductDTO product) throws ProductNotFoundException{
        aux = new ProductEntity();

        int checker = productRules(product);
        if (checker >= 1 && checker < 8)
            return checker;

        dto_entity(product, aux);

        return productRepo.updateProduct(id,aux);
    }

    public int updatePromotionCategory(String category, float percent){
        if(verifyCategory(category)){
            return 1;
        }
        if(verifyPercent(percent)){
            return 2;
        }
        return productRepo.updatePromotionProductCategory(category,percent);
    }

    public int updatePromotionBrand(String brand, float percent){
        if(verifyBrand(brand)){
            return 1;
        }
        if(verifyPercent(percent)){
            return 2;
        }
        return productRepo.updatePromotionProductBrand(brand,percent);
    }

    public int updatePromotionAll(float percent){
        if(verifyPercent(percent)){
            return 1;
        }
        return productRepo.updatePromotionProduct(percent);
    }

    private boolean firstVerifyNumber(String str){
        if(str.substring(0,1).equals("0") || str.substring(0,1).equals("1") || str.substring(0,1).equals("2") || str.substring(0,1).equals("3") || str.substring(0,1).equals("4") || str.substring(0,1).equals("5") || str.substring(0,1).equals("6") || str.substring(0,1).equals("7") || str.substring(0,1).equals("8") || str.substring(0,1).equals("9")){
            return true;
        }
        return false;
    }
    private boolean firstVerifySpecial(String str){
        if(str.substring(0,1).equals("@") || str.substring(0,1).equals("#") || str.substring(0,1).equals("$") || str.substring(0,1).equals("%") || str.substring(0,1).equals("&") || str.substring(0,1).equals("*") || str.substring(0,1).equals("_")){
            return true;
        }
        return false;
    }
    private boolean verifySpecial(String str){
        if(str.contains(".") || str.contains("_") || str.contains("$") || str.contains("@") || str.contains("#") || str.contains("%") || str.contains("*") || str.contains("&")){
            return true;
        }
        return false;
    }
    private boolean verifyNumber(String str){
        if(str.contains("0") || str.contains("1") || str.contains("2") || str.contains("3") || str.contains("4") || str.contains("5") || str.contains("6") || str.contains("7") || str.contains("8") || str.contains("9")){
            return true;
        }
        return false;
    }

    private void entity_dto(List<ProductDTO> listProducts, List<ProductEntity> aux) {
        for (ProductEntity tmp : aux) {
            dto = new ProductDTO();
            dto.setName(tmp.getName());
            dto.setBrand(tmp.getBrand());
            dto.setQuantity(tmp.getQuantity());
            dto.setPromotion(tmp.isPromotion());
            dto.setPrice(tmp.getPriceDto());
            dto.setDescription(tmp.getDescription());
            dto.setPercent(tmp.getPercent());
            dto.setCategory(tmp.getCategory());

            listProducts.add(dto);
        }
    }

    private int productRules(ProductDTO product) {
        if(!(product.getName().trim().substring(0,1).equals(product.getName().trim().substring(0,1).toUpperCase())) || product.getName().equals("")){
            return 1;
        }
        if(!(product.getBrand().trim().substring(0,1).equals(product.getBrand().trim().substring(0,1).toUpperCase())) || product.getBrand().equals("")){
            return 2;
        }
        if(product.getQuantity() <= 0){
            return 3;
        }
        if(product.getPrice() <= 0.0){
            return 4;
        }
        if(!(product.getCategory().trim().substring(0,1).equals(product.getCategory().trim().substring(0,1).toUpperCase())) || product.getCategory().equals("") || firstVerifySpecial(product.getCategory().trim()) || firstVerifyNumber(product.getCategory().trim()) || verifySpecial(product.getCategory().trim()) || verifyNumber(product.getCategory().trim())){
            return 5;
        }
        if(product.getPercent() < 0.0){
            return 6;
        }
        if(product.getDescription().trim().split(" ").length < 2 || firstVerifyNumber(product.getDescription().trim()) || firstVerifySpecial(product.getDescription().trim()) || !product.getDescription().trim().substring(0,1).equals(product.getDescription().trim().substring(0,1).toUpperCase())){
            return 7;
        }
        return 8;
    }

    private void dto_entity(ProductDTO product, ProductEntity aux) {
        aux.setName(product.getName());
        aux.setBrand(product.getBrand());
        aux.setQuantity(product.getQuantity());
        aux.setPromotion(product.isPromotion());
        aux.setPrice(product.getPrice());
        aux.setDescription(product.getDescription());
        aux.setPercent(product.getPercent());
        aux.setCategory(product.getCategory());
    }

    private boolean verifyPercent(float percent){
        if(percent < 0.0)
            return true;
        return false;
    }

    private boolean verifyBrand(String brand){
        if(!(brand.trim().substring(0,1).equals(brand.trim().substring(0,1).toUpperCase())) || brand.equals(""))
            return true;
        return false;
    }

    private boolean verifyCategory(String category){
        if(!(category.trim().substring(0,1).equals(category.trim().substring(0,1).toUpperCase())) || category.equals("") || firstVerifySpecial(category.trim()) || firstVerifyNumber(category.trim()) || verifySpecial(category.trim()) || verifyNumber(category.trim())){
            return true;
        }
        return false;
    }
}
