package br.iesb.imarket.controller;

import br.iesb.imarket.dto.request.ProductDTO;
import br.iesb.imarket.dto.response.MessageResponseDTO;
import br.iesb.imarket.dto.response.MessageSingletron;
import br.iesb.imarket.exception.ProductBadRequestException;
import br.iesb.imarket.exception.ProductNotFoundException;
import br.iesb.imarket.exception.UserNotFoundException;
import br.iesb.imarket.exception.UserUnauthorizedException;
import br.iesb.imarket.service.AuthService;
import br.iesb.imarket.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductController {
    @Autowired
    private ProductService serviceProd;
    @Autowired
    private AuthService serviceAuth;

    private MessageSingletron msg = MessageSingletron.getMessageSingletron();

    private MessageResponseDTO message = new MessageResponseDTO();

    @GetMapping("/products")
    public ResponseEntity<List<ProductDTO>> getProducts() throws ProductNotFoundException{
        List<ProductDTO> listProductsDtos = serviceProd.getProduct();
        return ResponseEntity.ok(listProductsDtos);
    }
    @GetMapping("/products/category")
    public ResponseEntity<List<ProductDTO>> getProductsCategory(@RequestParam(value="category") String category) throws ProductNotFoundException, ProductBadRequestException{
        List<ProductDTO> listProductsDtos = serviceProd.getProductCategory(category);
        return ResponseEntity.ok(listProductsDtos);
    }
    @GetMapping("/products/brand")
    public ResponseEntity<List<ProductDTO>> getProductsBrand(@RequestParam(value="brand") String brand) throws ProductNotFoundException, ProductBadRequestException{
        List<ProductDTO> listProductsDtos = serviceProd.getProductBrand(brand);
        return ResponseEntity.ok(listProductsDtos);
    }
    @GetMapping("/products/promotion")
    public ResponseEntity<List<ProductDTO>> getProductsPromotion() throws ProductNotFoundException{
        List<ProductDTO> listProductsDtos = serviceProd.getProductsPromotion();
        return ResponseEntity.ok(listProductsDtos);
    }
    @GetMapping("/products/crescente")
    public ResponseEntity<List<ProductDTO>> getProductsCrescente() throws ProductNotFoundException{
        List<ProductDTO> listProductsDtos = serviceProd.getProductsCrescente();
        if(listProductsDtos.size() == 0){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(listProductsDtos);
    }

    @PostMapping("/products/save/{idUser}")
    public ResponseEntity<MessageResponseDTO> postProducts(@RequestBody ProductDTO product, @PathVariable Long idUser) throws ProductBadRequestException, UserUnauthorizedException, UserNotFoundException {
        verfiyUser(idUser);
        serviceProd.saveProduct(product);
        return ResponseEntity.status(HttpStatus.CREATED).body(msg.withMessage("Product created").build());
    }

    @PutMapping("/products/update/{idUser}")
    public ResponseEntity<MessageResponseDTO> putProduct(@RequestParam(value="id") long id, @RequestBody ProductDTO productDto, @PathVariable Long idUser) throws ProductNotFoundException, ProductBadRequestException, UserUnauthorizedException, UserNotFoundException {
        verfiyUser(idUser);
        serviceProd.updateProduct(id,productDto);
        return ResponseEntity.status(HttpStatus.OK).body(msg.withMessage("Product updated").build());
    }
    @PutMapping("/products/update/promotionCategory/{idUser}")
    public ResponseEntity<MessageResponseDTO> putProductsCategory(@RequestParam(value="category")  String category, @RequestParam(value="percent")  float percent, @PathVariable Long idUser) throws ProductNotFoundException, ProductBadRequestException, UserUnauthorizedException, UserNotFoundException {
        verfiyUser(idUser);
        serviceProd.updatePromotionCategory(category,percent);
        return ResponseEntity.ok().body(msg.withMessage("Updated products").build());
    }
    @PutMapping("/products/update/promotionBrand/{idUser}")
    public ResponseEntity<MessageResponseDTO> putProductsBrand(@RequestParam(value="brand") String brand, @RequestParam(value="percent") float percent, @PathVariable Long idUser) throws ProductNotFoundException, ProductBadRequestException, UserUnauthorizedException, UserNotFoundException {
        verfiyUser(idUser);
        serviceProd.updatePromotionBrand(brand,percent);
        return ResponseEntity.ok().body(msg.withMessage("Updated products").build());
    }
    @PutMapping("/products/update/promotionAll/{idUser}")
    public ResponseEntity<MessageResponseDTO> putProductAll(@RequestParam(value="percent") float percent, @PathVariable Long idUser) throws ProductNotFoundException,ProductBadRequestException, UserUnauthorizedException, UserNotFoundException {
        verfiyUser(idUser);
        serviceProd.updatePromotionAll(percent);
        return ResponseEntity.ok().body(msg.withMessage("Updated products").build());
    }

    @DeleteMapping("/products/delete/{idUser}")
    public ResponseEntity<MessageResponseDTO> deleteProduct(@RequestParam(value="id") Long id, @PathVariable Long idUser) throws ProductNotFoundException , UserUnauthorizedException, UserNotFoundException {
        verfiyUser(idUser);
        serviceProd.serviceDel(id);
        return ResponseEntity.noContent().build();
    }
    @DeleteMapping("/products/delete/category/{idUser}")
    public ResponseEntity<MessageResponseDTO> deleteProductCategory(@RequestParam(value="category") String category, @PathVariable Long idUser) throws ProductNotFoundException, ProductBadRequestException, UserUnauthorizedException, UserNotFoundException {
        verfiyUser(idUser);
        serviceProd.serviceDelCategory(category);
        return ResponseEntity.noContent().build();
    }
    @DeleteMapping("/products/delete/brand/{idUser}")
    public ResponseEntity<MessageResponseDTO> deleteProductBrand(@RequestParam(value="brand") String brand, @PathVariable Long idUser) throws ProductNotFoundException, ProductBadRequestException, UserUnauthorizedException, UserNotFoundException {
        verfiyUser(idUser);
        serviceProd.serviceDelBrand(brand);
        return ResponseEntity.noContent().build();
    }
    @DeleteMapping("/products/delete/all/{idUser}")
    public ResponseEntity<MessageResponseDTO> deleteProductAll(@PathVariable Long idUser) throws ProductNotFoundException, UserUnauthorizedException, UserNotFoundException {
        verfiyUser(idUser);
        serviceProd.serviceDelAll();
        return ResponseEntity.noContent().build();
    }

    private void verfiyUser(Long idUser) throws UserUnauthorizedException, UserNotFoundException {
        if(!serviceAuth.getLogById(idUser).isAcess()){
            throw new UserUnauthorizedException("User unauthorized Whit ID "+ idUser);
        }
    }
}