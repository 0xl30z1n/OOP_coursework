package br.iesb.imarket.repository;

import br.iesb.imarket.model.ProductEntity;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Repository
public class ProductRepository {
    private List<ProductEntity> bankSimulator = new ArrayList<>();

    public List<ProductEntity> getProducts(){
        return bankSimulator;
    }

    public List<ProductEntity> getProductsCategory(String category){
        List<ProductEntity> productsCategory = new ArrayList<>();
        for(ProductEntity aux : bankSimulator){
            if(aux.getCategory().equals(category)){
                productsCategory.add(aux);
            }
        }
        return productsCategory;
    }

    public List<ProductEntity> getProductsBrand(String brand){
        List<ProductEntity> productsBrand = new ArrayList<>();
        for(ProductEntity aux : bankSimulator){
            if(aux.getCategory().equals(brand)){
                productsBrand.add(aux);
            }
        }
        return productsBrand;
    }

    public List<ProductEntity> getProductsPromotion(){
        List<ProductEntity> productsPromotion = new ArrayList<>();
        for(ProductEntity aux : bankSimulator){
            if(aux.isPromotion()){
                productsPromotion.add(aux);
            }
        }
        return productsPromotion;
    }

    public List<ProductEntity> getProductsCrescente(){
        List<ProductEntity> productsCrescente = new ArrayList<>(bankSimulator);
        quickSort(productsCrescente,0,productsCrescente.size()-1);
        return productsCrescente;
    }

    private void quickSort(List<ProductEntity> vet, int inicio, int fim) {
        if (inicio < fim) {
            int posicaoPivo = separar(vet, inicio, fim);
            quickSort(vet, inicio, posicaoPivo - 1);
            quickSort(vet, posicaoPivo + 1, fim);
        }
    }
    private int separar(List<ProductEntity> vet, int inicio, int fim) {
        int pivor,aux;
        pivor = inicio;
        aux = fim;

        while(pivor != aux){
            if(pivor < aux){
                if(vet.get(pivor).getPrice() > vet.get(aux).getPrice()){
                    swap(vet,pivor,aux);
                }else{
                    aux--;
                }
            }else{
                if(vet.get(pivor).getPrice() < vet.get(aux).getPrice()){
                    swap(vet,pivor,aux);
                }else{
                    aux++;
                }
            }
        }
        return pivor;
    }

    private void swap(List<ProductEntity> vet, int pivor, int aux){
        ProductEntity productAux = new ProductEntity();
        productAux.setName(vet.get(pivor).getName());
        productAux.setBrand(vet.get(pivor).getBrand());
        productAux.setId(vet.get(pivor).getId());
        productAux.setCategory(vet.get(pivor).getCategory());
        productAux.setCreate(vet.get(pivor).getCreate());
        productAux.setUpdate(vet.get(pivor).getUpdate());
        productAux.setDescription(vet.get(pivor).getDescription());
        productAux.setQuantity(vet.get(pivor).getQuantity());
        productAux.setPromotion(vet.get(pivor).isPromotion());
        productAux.setPercent(vet.get(pivor).getPercent());
        productAux.setPrice(vet.get(pivor).getPrice());

        vet.get(pivor).setName(vet.get(aux).getName());
        vet.get(pivor).setBrand(vet.get(aux).getBrand());
        vet.get(pivor).setId(vet.get(aux).getId());
        vet.get(pivor).setCategory(vet.get(aux).getCategory());
        vet.get(pivor).setCreate(vet.get(aux).getCreate());
        vet.get(pivor).setUpdate(vet.get(aux).getUpdate());
        vet.get(pivor).setDescription(vet.get(aux).getDescription());
        vet.get(pivor).setQuantity(vet.get(aux).getQuantity());
        vet.get(pivor).setPromotion(vet.get(aux).isPromotion());
        vet.get(pivor).setPercent(vet.get(aux).getPercent());
        vet.get(pivor).setPrice(vet.get(aux).getPrice());

        vet.get(aux).setName(vet.get(aux).getName());
        vet.get(aux).setBrand(productAux.getBrand());
        vet.get(aux).setId(productAux.getId());
        vet.get(aux).setCategory(productAux.getCategory());
        vet.get(aux).setCreate(productAux.getCreate());
        vet.get(aux).setUpdate(productAux.getUpdate());
        vet.get(aux).setDescription(productAux.getDescription());
        vet.get(aux).setQuantity(productAux.getQuantity());
        vet.get(aux).setPromotion(productAux.isPromotion());
        vet.get(aux).setPercent(productAux.getPercent());
        vet.get(aux).setPrice(productAux.getPrice());
    }
    public void saveProduct(ProductEntity product){
        product.setId(bankSimulator.size()+1);
        bankSimulator.add(product);
    }

    public boolean updateProduct(long id, ProductEntity product){
        boolean aux = false;
        for(int i = 0; i < bankSimulator.size()-1 && !aux; i++){
            if(bankSimulator.get(i).getId() == id){
                bankSimulator.get(i).setName(product.getName());
                bankSimulator.get(i).setBrand(product.getBrand());
                bankSimulator.get(i).setDescription(product.getDescription());
                bankSimulator.get(i).setQuantity(product.getQuantity());
                bankSimulator.get(i).setCategory(product.getCategory());
                bankSimulator.get(i).setPromotion(product.isPromotion());
                if(product.isPromotion() == true){
                    bankSimulator.get(i).setPercent(product.getPercent());
                }
                bankSimulator.get(i).setPrice(product.getPrice());
                Date data = new Date();
                bankSimulator.get(i).setUpdate(data);
                aux = true;
            }
        }
        return aux;
    }

    public void updatePromotionProductCategory(String category, float percent){
        for(int i = 0; i < bankSimulator.size()-1; i++){
            if(bankSimulator.get(i).getCategory().equals(category)){
                bankSimulator.get(i).setPromotion(true);
                bankSimulator.get(i).setPercent(percent);
                Date data = new Date();
                bankSimulator.get(i).setUpdate(data);
            }
        }
    }

    public void updatePromotionProductBrand(String brand, float percent){
        for(int i = 0; i < bankSimulator.size()-1; i++){
            if(bankSimulator.get(i).getCategory().equals(brand)){
                bankSimulator.get(i).setPromotion(true);
                bankSimulator.get(i).setPercent(percent);
                Date data = new Date();
                bankSimulator.get(i).setUpdate(data);
            }
        }
    }

    public void updatePromotionProduct(float percent){
        for(int i = 0; i < bankSimulator.size()-1; i++){
            bankSimulator.get(i).setPromotion(true);
            bankSimulator.get(i).setPercent(percent);
            Date data = new Date();
            bankSimulator.get(i).setUpdate(data);
        }
    }

    public int deleteProduct(long id){
        for(int i = 0; i < bankSimulator.size()-1; i++){
            if(bankSimulator.get(i).getId() == id){
                bankSimulator.remove(i);
                return 0;
            }
        }
        return 1;
    }

    public void deleteProductCategory(String category){
        for(int i = 0; i < bankSimulator.size()-1; i++){
            if(bankSimulator.get(i).getCategory().equals(category)){
                bankSimulator.remove(i);
            }
        }
    }

    public void deleteProductBrand(String brand){
        for(int i = 0; i < bankSimulator.size()-1; i++){
            if(bankSimulator.get(i).getCategory().equals(brand)){
                bankSimulator.remove(i);
            }
        }
    }

    public void deleteAll(){
        bankSimulator.clear();
    }
}
