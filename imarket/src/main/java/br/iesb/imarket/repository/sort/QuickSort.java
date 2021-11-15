package br.iesb.imarket.repository.sort;

import br.iesb.imarket.model.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class QuickSort extends SortProducts{
    private List<Product> list;
    private int i;
    private int f;

    @Override
    public void sort(){
        quickSort(this.list,this.i,this.f);
    }

    private void quickSort(List<Product> list,int inicio,int fim){
        if (inicio < fim) {
            int posicaoPivo = separar(list, inicio, fim);
            quickSort(list, inicio, posicaoPivo - 1);
            quickSort(list, posicaoPivo + 1, fim);
        }
    }

    private int separar(List<Product> list, int inicio, int fim) {
        int pivor,aux;
        pivor = inicio;
        aux = fim;

        while(pivor != aux){
            if(pivor < aux){
                if(list.get(pivor).getPriceDto() > list.get(aux).getPriceDto()){
                    swap(list,pivor,aux);
                }else{
                    aux--;
                }
            }else{
                if(list.get(pivor).getPriceDto() < list.get(aux).getPriceDto()){
                    swap(list,pivor,aux);
                }else{
                    aux++;
                }
            }
        }
        return pivor;
    }

    private void swap(List<Product> list, int pivor, int aux){
        Product productAux = new Product(list.get(pivor).getId(),list.get(pivor).getName(),list.get(pivor).getBrand(),list.get(pivor).getPrice(),list.get(pivor).getQuantity(),list.get(pivor).getDescription(),list.get(pivor).getPercent(),list.get(pivor).getCreationDate(),list.get(pivor).getUpdatingDate(),list.get(pivor).isPromotion(),list.get(pivor).getCategory());

        list.get(pivor).setName(list.get(aux).getName());
        list.get(pivor).setBrand(list.get(aux).getBrand());
        list.get(pivor).setId(list.get(aux).getId());
        list.get(pivor).setCategory(list.get(aux).getCategory());
        list.get(pivor).setCreationDate(list.get(aux).getCreationDate());
        list.get(pivor).setUpdatingDate(list.get(aux).getUpdatingDate());
        list.get(pivor).setDescription(list.get(aux).getDescription());
        list.get(pivor).setQuantity(list.get(aux).getQuantity());
        list.get(pivor).setPrice(list.get(aux).getPrice());
        list.get(pivor).setPromotion(list.get(aux).isPromotion());
        list.get(pivor).setPercent(list.get(aux).getPercent());

        list.get(aux).setName(productAux.getName());
        list.get(aux).setBrand(productAux.getBrand());
        list.get(aux).setId(productAux.getId());
        list.get(aux).setCategory(productAux.getCategory());
        list.get(aux).setCreationDate(productAux.getCreationDate());
        list.get(aux).setUpdatingDate(productAux.getUpdatingDate());
        list.get(aux).setDescription(productAux.getDescription());
        list.get(aux).setQuantity(productAux.getQuantity());
        list.get(aux).setPrice(productAux.getPrice());
        list.get(aux).setPromotion(productAux.isPromotion());
        list.get(aux).setPercent(productAux.getPercent());
    }
}
