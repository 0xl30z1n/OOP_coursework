package br.iesb.imarket.service;

import br.iesb.imarket.dto.request.ShopCartDTO;
import br.iesb.imarket.dto.request.ShopCartItemDTO;
import br.iesb.imarket.dto.request.builder.ShopCartDTOBuilder;
import br.iesb.imarket.dto.request.builder.ShopCartItemDTOBuilder;
import br.iesb.imarket.model.ShopCart;
import br.iesb.imarket.model.ShopCartItem;
import br.iesb.imarket.model.builder.ShopCartBuilder;
import br.iesb.imarket.model.builder.ShopCartItemBuilder;
import br.iesb.imarket.repository.ShopCartRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ShopCartService {

    @Autowired
    private ShopCartRepo repositoryCart;

    public ShopCartDTO getCart(Long id){
        return entityForDtoCart(verifyIfCartExists(id));
    }

    public void deleteCart(Long id){
        verifyIfCartExists(id);
        repositoryCart.deleteById(id);
    }

    public void saveCart(Long id, ShopCartDTO cardDto){
        repositoryCart.save(dtoForEntity(cardDto,id));
    }

    private ShopCart verifyIfCartExists(Long idUser){
        ShopCart cart = repositoryCart.findById(idUser).orElseThrow();
        return cart;
    }

    private ShopCartDTO entityForDtoCart(ShopCart cart){
        ShopCartDTOBuilder builder = ShopCartDTOBuilder.builder();
        List<ShopCartItemDTO> list = new ArrayList<>();
        for(ShopCartItem item: cart.getItems()){
            list.add(entityForDtoItem(item));
        }
        ShopCartDTO dto = builder
                .withId(cart.getId())
                .withTotal(cart.getTotal())
                .withItems(list).
                withDate(cart.getLastModification())
                .build();
        return dto;
    }

    private ShopCartItemDTO entityForDtoItem(ShopCartItem item){
        ShopCartItemDTOBuilder builder = ShopCartItemDTOBuilder.builder();

        ShopCartItemDTO dto = builder
                .withId(item.getId())
                .withQuantity(item.getQuantity())
                .build();
        return dto;
    }

    private ShopCartItem dtoForEntityItem(ShopCartItemDTO item){
        ShopCartItemBuilder builder = ShopCartItemBuilder.builder();

        ShopCartItem entity = builder
                .withQuantity(item.getQuantity())
                .withPrice(item.getPrice())
                .build();

        return entity;
    }

    private ShopCart dtoForEntity(ShopCartDTO dto, Long id){
        ShopCartBuilder builder = ShopCartBuilder.builder();
        List<ShopCartItem> list = new ArrayList<>();
        for(ShopCartItemDTO item: dto.getItems()){
            list.add(dtoForEntityItem(item));
        }
        Date data = new Date();
        ShopCart cart = builder
                .withId(id)
                .withTotal(dto.getTotal())
                .withDate(data)
                .withItems(list)
                .build();
        return cart;
    }
}
