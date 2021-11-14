package br.iesb.imarket.service;

import br.iesb.imarket.dto.request.CategoryDTO;
import br.iesb.imarket.dto.request.ProductDTO;
import br.iesb.imarket.model.Category;
import br.iesb.imarket.model.Product;
import br.iesb.imarket.repository.CategoryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {
    @Autowired
    CategoryRepo categoryRepo;

    public List<Category> getAllCategories(){
        return categoryRepo.findAll();
    }

    public void addCategory(CategoryDTO category){
        Category newCategory = new Category();
        dtoForEntity(category, newCategory);
        categoryRepo.save(newCategory);
    }

    public void removeCategoryById(int id){
        categoryRepo.deleteById(id);
    }

    public Optional<Category> getCategoryById(int id){
        return categoryRepo.findById(id);
    }

    private void dtoForEntity(CategoryDTO category, Category aux) {
        aux.setId(category.getId());
        aux.setName(category.getName());
    }
}
