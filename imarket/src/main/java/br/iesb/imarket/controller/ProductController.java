package br.iesb.imarket.controller;

import br.iesb.imarket.dto.request.ProductDTO;
import br.iesb.imarket.dto.response.MessageResponseDTO;
import br.iesb.imarket.exception.ProductNotFoundException;
import br.iesb.imarket.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductController {
    private ProductService service = new ProductService();
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
        int result = service.saveProduct(product);
        switch (result){
            case 1:
                message.setMessage("Nome do produto inválido!");
                return ResponseEntity.badRequest().body(message);
            case 2:
                message.setMessage("Nome da marca inválida!");
                return ResponseEntity.badRequest().body(message);
            case 3:
                message.setMessage("Quantidade inválida!");
                return ResponseEntity.badRequest().body(message);
            case 4:
                message.setMessage("Preço inválido!");
                return ResponseEntity.badRequest().body(message);
            case 5:
                message.setMessage("Nome da categoria inválida!");
                return ResponseEntity.badRequest().body(message);
            case 6:
                message.setMessage("Percentual inválido!");
                return ResponseEntity.badRequest().body(message);
            case 7:
                message.setMessage("Descrição inválida!");
                return ResponseEntity.badRequest().body(message);
            default:
                break;
        }
        message.setMessage("Product created");
        return ResponseEntity.status(HttpStatus.CREATED).body(message);
    }

    @PutMapping("/updateProduct")
    public ResponseEntity<MessageResponseDTO> putProduct(@RequestParam(value="id") long id, @RequestBody ProductDTO productDto) throws ProductNotFoundException{
        int result = service.updateProduct(id,productDto);
        switch (result){
            case -1:
                return ResponseEntity.notFound().build();
            case 1:
                message.setMessage("Nome do produto inválido!");
                return ResponseEntity.badRequest().body(message);
            case 2:
                message.setMessage("Nome da marca inválida!");
                return ResponseEntity.badRequest().body(message);
            case 3:
                message.setMessage("Quantidade inválida!");
                return ResponseEntity.badRequest().body(message);
            case 4:
                message.setMessage("Preço inválido!");
                return ResponseEntity.badRequest().body(message);
            case 5:
                message.setMessage("Nome da categoria inválida!");
                return ResponseEntity.badRequest().body(message);
            case 6:
                message.setMessage("Percentual inválido!");
                return ResponseEntity.badRequest().body(message);
            case 7:
                message.setMessage("Descrição inválida!");
                return ResponseEntity.badRequest().body(message);
            default:
                break;
        }
        message.setMessage("Product updated");
        return ResponseEntity.status(HttpStatus.OK).body(message);
    }
    @PutMapping("/updateProduct/promotionCategory")
    public ResponseEntity<MessageResponseDTO> putProductsCategory(@RequestParam(value="category")  String category, @RequestParam(value="percent")  float percent){
        int result = service.updatePromotionCategory(category,percent);
        if(result == -1){
            return ResponseEntity.notFound().build();
        }else if(result == 1){
            message.setMessage("Categoria inválida!");
            return ResponseEntity.badRequest().body(message);
        }else if(result == 2){
            message.setMessage("Percentual inválido!");
            return ResponseEntity.badRequest().body(message);
        }
        message.setMessage("Updated products");
        return ResponseEntity.ok().body(message);
    }
    @PutMapping("/updateProduct/promotionBrand")
    public ResponseEntity<MessageResponseDTO> putProductsBrand(@RequestParam(value="brand") String brand, @RequestParam(value="percent") float percent){
        int result = service.updatePromotionBrand(brand,percent);
        if(result == -1){
            return ResponseEntity.notFound().build();
        }else if(result == 1){
            message.setMessage("Marca inválida!");
            return ResponseEntity.badRequest().body(message);
        }else if(result == 2){
            message.setMessage("Percentual inválido!");
            return ResponseEntity.badRequest().body(message);
        }
        message.setMessage("Updated products");
        return ResponseEntity.ok().body(message);
    }
    @PutMapping("/updateProduct/promotionAll")
    public ResponseEntity<MessageResponseDTO> putProductAll(@RequestParam(value="percent") float percent){
        int result = service.updatePromotionAll(percent);
        if(result == -1){
            return ResponseEntity.notFound().build();
        }else if(result == 1){
            message.setMessage("Percentual inválido!");
            return ResponseEntity.badRequest().body(message);
        }
        message.setMessage("Updated products");
        return ResponseEntity.ok().body(message);
    }

    @DeleteMapping("/deleteProduct")
    public ResponseEntity<MessageResponseDTO> deleteProduct(@RequestParam(value="id") long id) throws ProductNotFoundException{
        int result = service.serviceDel(id);
        if(result == 1){
            return ResponseEntity.notFound().build();
        }else if(result == -1){
            message.setMessage("Id inválido!");
            return ResponseEntity.badRequest().body(message);
        }
        return ResponseEntity.noContent().build();
    }
    @DeleteMapping("/deleteProduct/category")
    public ResponseEntity<MessageResponseDTO> deleteProductCategory(@RequestParam(value="category") String category){
        int result = service.serviceDelCategory(category);
        if(result == 0){
            return ResponseEntity.notFound().build();
        }else if(result == -1){
            message.setMessage("Categoria inválida!");
            return ResponseEntity.badRequest().body(message);
        }
        return ResponseEntity.noContent().build();
    }
    @DeleteMapping("/deleteProduct/brand")
    public ResponseEntity<MessageResponseDTO> deleteProductBrand(@RequestParam(value="brand") String brand){
        int result = service.serviceDelBrand(brand);
        if(result == 0){
            return ResponseEntity.notFound().build();
        }else if(result == -1){
            message.setMessage("Marca inválida!");
            return ResponseEntity.badRequest().body(message);
        }
        return ResponseEntity.noContent().build();
    }
    @DeleteMapping("/deleteProduct/all")
    public ResponseEntity<MessageResponseDTO> deleteProductAll(){
        service.serviceDelAll();
        return ResponseEntity.noContent().build();
    }
}
