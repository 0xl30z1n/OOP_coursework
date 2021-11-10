package br.iesb.imarket.controller;

import br.iesb.imarket.dto.request.ProductDTO;
import br.iesb.imarket.dto.response.MessageResponseDTO;
import br.iesb.imarket.exception.ProductNotFoundException;
import br.iesb.imarket.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductController {
    @Autowired
    private ProductService service;

    private MessageResponseDTO message = new MessageResponseDTO();

    @GetMapping("/products")
    public ResponseEntity<List<ProductDTO>> getProducts(){
        List<ProductDTO> listProductsDtos = service.getProduct();
        if(listProductsDtos.size() == 0){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(listProductsDtos);
    }
    @GetMapping("/products/category")
    public ResponseEntity<List<ProductDTO>> getProductsCategory(@RequestParam(value="category") String category){
        List<ProductDTO> listProductsDtos = service.getProductCategory(category);
        if(listProductsDtos.size() == 0){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(listProductsDtos);
    }
    @GetMapping("/products/brand")
    public ResponseEntity<List<ProductDTO>> getProductsBrand(@RequestParam(value="brand") String brand){
        List<ProductDTO> listProductsDtos = service.getProductBrand(brand);
        if(listProductsDtos.size() == 0){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(listProductsDtos);
    }
    @GetMapping("/products/promotion")
    public ResponseEntity<List<ProductDTO>> getProductsBrand(){
        List<ProductDTO> listProductsDtos = service.getProductsPromotion();
        if(listProductsDtos.size() == 0){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(listProductsDtos);
    }
    @GetMapping("/products/crescente")
    public ResponseEntity<List<ProductDTO>> getProductsCrescente(){
        List<ProductDTO> listProductsDtos = service.getProductsCrescente();
        if(listProductsDtos.size() == 0){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(listProductsDtos);
    }

    @PostMapping("/saveProduct")
    public ResponseEntity<MessageResponseDTO> postProducts(@RequestBody ProductDTO product){
        service.saveProduct(product);
        message.setMessage("Product created");
        return ResponseEntity.status(HttpStatus.CREATED).body(message);
    }

    @PutMapping("/updateProduct")
    public ResponseEntity<MessageResponseDTO> putProduct(@RequestParam(value="id") long id, @RequestBody ProductDTO productDto) throws ProductNotFoundException{
        service.updateProduct(id,productDto);
        message.setMessage("Product updated");
        return ResponseEntity.status(HttpStatus.OK).body(message);
    }
    @PutMapping("/updateProduct/promotionCategory")
    public ResponseEntity<MessageResponseDTO> putProductsCategory(@RequestParam(value="category")  String category, @RequestParam(value="percent")  float percent){
        service.updatePromotionCategory(category,percent);
        message.setMessage("Updated products");
        return ResponseEntity.ok().body(message);
    }
    @PutMapping("/updateProduct/promotionBrand")
    public ResponseEntity<MessageResponseDTO> putProductsBrand(@RequestParam(value="brand") String brand, @RequestParam(value="percent") float percent){
        service.updatePromotionBrand(brand,percent);
        message.setMessage("Updated products");
        return ResponseEntity.ok().body(message);
    }
    @PutMapping("/updateProduct/promotionAll")
    public ResponseEntity<MessageResponseDTO> putProductAll(@RequestParam(value="percent") float percent){
        service.updatePromotionAll(percent);
        message.setMessage("Updated products");
        return ResponseEntity.ok().body(message);
    }

    @DeleteMapping("/deleteProduct")
    public ResponseEntity<MessageResponseDTO> deleteProduct(@RequestParam(value="id") Long id) throws ProductNotFoundException{
        service.serviceDel(id);
        return ResponseEntity.noContent().build();
    }
    @DeleteMapping("/deleteProduct/category")
    public ResponseEntity<MessageResponseDTO> deleteProductCategory(@RequestParam(value="category") String category) throws ProductNotFoundException{
        service.serviceDelCategory(category);
        return ResponseEntity.noContent().build();
    }
    @DeleteMapping("/deleteProduct/brand")
    public ResponseEntity<MessageResponseDTO> deleteProductBrand(@RequestParam(value="brand") String brand){
        service.serviceDelBrand(brand);
        return ResponseEntity.noContent().build();
    }
    @DeleteMapping("/deleteProduct/all")
    public ResponseEntity<MessageResponseDTO> deleteProductAll(){
        service.serviceDelAll();
        return ResponseEntity.noContent().build();
    }
}