package br.iesb.imarket.controller;

import br.iesb.imarket.dto.ProductDTO;
import br.iesb.imarket.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductController {
    private ProductService service = new ProductService();

    @GetMapping("/get-products")
    public List<ProductDTO> getProducts(){
        List<ProductDTO> listProductsDtos = service.getProduct();
        return listProductsDtos;
    }

    @GetMapping("/get-productsCategory")
    public List<ProductDTO> getProductsCategory(@RequestParam(value="category") String category){
        List<ProductDTO> listProductsDtos = service.getProductCategory(category);
        return listProductsDtos;
    }

    @GetMapping("/get-productsBrand")
    public List<ProductDTO> getProductsBrand(@RequestParam(value="brand") String brand){
        List<ProductDTO> listProductsDtos = service.getProductBrand(brand);
        return listProductsDtos;
    }

    @GetMapping("/get-productsPromotion")
    public List<ProductDTO> getProductsBrand(){
        List<ProductDTO> listProductsDtos = service.getProductsPromotion();
        return listProductsDtos;
    }

    @GetMapping("/get-productsCrescente")
    public List<ProductDTO> getProductsCrescente(){
        List<ProductDTO> listProductsDtos = service.getProductsCrescente();
        return listProductsDtos;
    }
    @PostMapping("/post-products")
    public ResponseEntity<String> postProducts(@RequestBody ProductDTO product){
        service.dto_entity(product);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/put-product")
    public ResponseEntity<String> putProduct(@RequestParam(value="id") long id, @RequestBody ProductDTO productDto){
        boolean result = service.dto_entity_semID(id,productDto);
        if(!result){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().build();
    }

    @PutMapping("/put-promotionProductsCategory")
    public ResponseEntity<String> putProductsCategory(@RequestParam(value="category")  String category, @RequestParam(value="percent")  float percent){
        service.updatePromotionCategory(category,percent);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/put-promotionProductsBrand")
    public ResponseEntity<String> putProductsBrand(@RequestParam(value="brand") String brand, @RequestParam(value="percent") float percent){
        service.updatePromotionBrand(brand,percent);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/put-promotionAllProducts")
    public ResponseEntity<String> putProductAll(@RequestParam(value="percent") float percent){
        service.updatePromotionAll(percent);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/delete-product")
    public ResponseEntity<String> deleteProduct(@RequestParam(value="id") long id){
        int result = service.serviceDel(id);

        if(result == 1){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().build();
    }
    @DeleteMapping("/delete-productCategory")
    public ResponseEntity<String> deleteProductCategory(@RequestParam(value="category") String category){
        service.serviceDelCategory(category);
        return ResponseEntity.ok().build();
    }
    @DeleteMapping("/delete-productBrand")
    public ResponseEntity<String> deleteProductBrand(@RequestParam String brand){
        service.serviceDelBrand(brand);
        return ResponseEntity.ok().build();
    }
    @DeleteMapping("/delete-allProduct")
    public ResponseEntity<String> deleteProductAll(){
        service.serviceDelAll();
        return ResponseEntity.ok().build();
    }
}
