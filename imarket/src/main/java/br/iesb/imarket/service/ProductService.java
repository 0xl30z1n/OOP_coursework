package br.iesb.imarket.service;

import br.iesb.imarket.dto.request.ProductDTO;
import br.iesb.imarket.enums.CategoryType;
import br.iesb.imarket.exception.ProductBadRequestException;
import br.iesb.imarket.exception.ProductNotFoundException;
import br.iesb.imarket.model.Product;
import br.iesb.imarket.repository.ProdRepo;
import br.iesb.imarket.repository.sort.QuickSort;
import br.iesb.imarket.repository.sort.SortProducts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ProductService {

    @Autowired
    private ProdRepo repository;

    public List<ProductDTO> getProduct() throws ProductNotFoundException{
        List<ProductDTO> listProducts = new ArrayList<>();
        entityForDto(listProducts, verifyIsBankEmpty());
        return listProducts;
    }

    public List<ProductDTO> getProductCategory(String category) throws ProductNotFoundException, ProductBadRequestException {
        verifyCategory(category);
        List<ProductDTO> listProducts = new ArrayList<>();
        entityForDto(listProducts, verifyIfCategoryExists(category));
        return listProducts;
    }

    public List<ProductDTO> getProductBrand(String brand) throws ProductNotFoundException, ProductBadRequestException{
        verifyBrand(brand);
        List<ProductDTO> listProducts = new ArrayList<>();
        entityForDto(listProducts, verifyIfBrandExists(brand));
        return listProducts;
    }

    public List<ProductDTO> getProductsPromotion() throws ProductNotFoundException{
        List<ProductDTO> listProducts = new ArrayList<>();
        entityForDto(listProducts, verifyIfPromotionExists());
        return listProducts;
    }

    public List<ProductDTO> getProductsCrescente() throws ProductNotFoundException{
        List<ProductDTO> listProducts = new ArrayList<>();
        List<Product> aux = verifyIsBankEmpty();
        SortProducts sort = new QuickSort(aux,0,aux.size()-1);
        sort.sort();
        entityForDto(listProducts,aux);
        return listProducts;
    }

    public int saveProduct(ProductDTO product) throws ProductBadRequestException{
        verifyIfNameExists(product.getName());
        productRules(product);
        Product aux = new Product();
        dtoForEntity(product, aux);
        Date data = new Date();
        aux.setCreationDate(data);
        repository.save(aux);
        return 0;
    }

    public void serviceDel(Long id) throws ProductNotFoundException{
        verifyIfExists(id);
        repository.deleteById(id);
    }
    public void serviceDelCategory(String category) throws ProductNotFoundException, ProductBadRequestException{
        verifyCategory(category);
        for (Product product : verifyIfCategoryExists(category)) {
            repository.deleteById(product.getId());
        }

    }
    public void serviceDelBrand(String brand) throws ProductNotFoundException, ProductBadRequestException{
        verifyBrand(brand);
        for (Product product : verifyIfBrandExists(brand)) {
            repository.deleteById(product.getId());
        }
    }
    public void serviceDelAll()throws ProductNotFoundException{
        verifyIsBankEmpty();
        repository.deleteAll();
    }

    public void updateProduct(long id, ProductDTO product) throws ProductNotFoundException, ProductBadRequestException{
        verifyIfExists(id);
        productRules(product);
        Product aux = new Product();
        aux.setId(id);
        dtoForEntity(product, aux);
        repository.save(aux);
    }

    public void updatePromotionCategory(String category, float percent) throws ProductNotFoundException, ProductBadRequestException{
        verifyCategory(category);
        verifyPercent(percent);
        //Optional<List<Product>> resultBank = repository.findByCategoryContaining(category);
        //changePercent(percent,resultBank.get(),"Selected category null");
    }

    public void updatePromotionBrand(String brand, float percent) throws ProductNotFoundException, ProductBadRequestException{
        Optional<List<Product>> resultBank = repository.findByBrandContaining(brand);
        changePercent(percent,resultBank.get(), "Selected brand null");
    }

    public void updatePromotionAll(float percent) throws ProductNotFoundException, ProductBadRequestException{
        Optional<List<Product>> resultBank = repository.getByPromotionIsTrue();
        changePercent(percent, resultBank.get(), "Bank is empty");
    }

    private void entityForDto(List<ProductDTO> listProducts, List<Product> resultBank) {
        for (Product product : resultBank) {
            ProductDTO dto = new ProductDTO(product.getName(),product.getBrand(),product.getPriceDto(),product.getQuantity(),product.getDescription(),product.isPromotion(),product.getPercent(),product.getCategory().getDescription());
            listProducts.add(dto);
        }
    }

    private void dtoForEntity(ProductDTO product, Product aux) {
        aux.setName(product.getName());
        aux.setBrand(product.getBrand());
        aux.setQuantity(product.getQuantity());
        aux.setPromotion(product.isPromotion());
        aux.setPrice(product.getPrice());
        aux.setDescription(product.getDescription());
        aux.setPercent(product.getPercent());
        CategoryType category = CategoryType.valueOf(product.getCategory());
        aux.setCategory(category);
    }

    private void verifyPercent(float percent) throws ProductBadRequestException{
        if(percent < 0.0)
            throw new ProductBadRequestException("Invalid product percent");
    }

    private void verifyBrand(String brand) throws ProductBadRequestException{
        if(!(brand.trim().substring(0,1).equals(brand.trim().substring(0,1).toUpperCase())) || brand.equals(""))
            throw new ProductBadRequestException("Invalid product brand");
    }

    private void verifyCategory(String category) throws ProductBadRequestException{
        CategoryType[] values = CategoryType.values();
        for (CategoryType c: values){
            if(c.name().equals(category)){
                return;
            }
        }
        throw new ProductBadRequestException("Invalid product category");
    }

    private Product verifyIfExists(Long id) throws ProductNotFoundException {
        return repository.findById(id).orElseThrow(()->new ProductNotFoundException(id));
    }

    private List<Product> verifyIsBankEmpty() throws ProductNotFoundException{
        Iterator<Product> resultBank = repository.findAll().iterator();
        List<Product> listProducts = new ArrayList<>();
        resultBank.forEachRemaining(listProducts::add);
        if(listProducts.isEmpty()){
            throw new ProductNotFoundException("Bank is empty");
        }
        return listProducts;
    }

    private List<Product> verifyIfCategoryExists(String category) throws ProductNotFoundException{
        Optional<List<Product>> resultBank = repository.findByCategoryContains(category.trim().toUpperCase());
        List<Product> list = resultBank.get();
        if(list.isEmpty()){
            throw new ProductNotFoundException("Resource not found with Category " + category);
        }
        return list;
    }

    private List<Product> verifyIfBrandExists(String brand) throws ProductNotFoundException{
        Optional<List<Product>> resultBank = repository.findByBrandContaining(brand);
        List<Product> list = resultBank.get();
        if(list.isEmpty()){
            throw new ProductNotFoundException("Resource not found with Brand " + brand);
        }
        return list;
    }

    private List<Product> verifyIfPromotionExists() throws ProductNotFoundException{
        Optional<List<Product>> resultBank = repository.getByPromotionIsTrue();
        List<Product> list = resultBank.get();
        if(list.isEmpty()){
            throw new ProductNotFoundException("Resource not found Promotion");
        }
        return list;
    }

    private void productRules(ProductDTO product) throws ProductBadRequestException{
        if(!(product.getName().trim().substring(0,1).equals(product.getName().trim().substring(0,1).toUpperCase())) || product.getName().equals("")){
            throw new ProductBadRequestException("Invalid product name");
        }
        if(!(product.getBrand().trim().substring(0,1).equals(product.getBrand().trim().substring(0,1).toUpperCase())) || product.getBrand().equals("")){
            throw new ProductBadRequestException("Invalid product brand");
        }
        if(product.getQuantity() <= 0){
            throw new ProductBadRequestException("Invalid product quantity");
        }
        if(product.getPrice() <= 0.0){
            throw new ProductBadRequestException("Invalid product price");
        }

        verifyCategory(product.getCategory());

        if(product.getPercent() < 0.0){
            throw new ProductBadRequestException("Invalid product percent");
        }
        if(product.getDescription().trim().split(" ").length < 2 || firstVerifyNumber(product.getDescription().trim()) || firstVerifySpecial(product.getDescription().trim()) || !product.getDescription().trim().substring(0,1).equals(product.getDescription().trim().substring(0,1).toUpperCase())){
            throw new ProductBadRequestException("Invalid product description");
        }
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

    private void changePercent(float percent, List<Product> resultBank,String str) throws ProductNotFoundException{
        if (!resultBank.isEmpty()) {
            for (Product product : resultBank) {
                product.setPercent(percent);
                repository.save(product);
            }
        }else {
            throw new ProductNotFoundException(str);
        }
    }

    private void verifyIfNameExists(String name) throws ProductBadRequestException{
        Optional<Product> result = repository.findByNameContaining(name);
        if(!result.isEmpty()){
            throw new ProductBadRequestException("Name of product already exists");
        }
    }
}