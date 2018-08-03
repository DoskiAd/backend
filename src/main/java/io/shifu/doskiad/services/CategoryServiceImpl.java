package io.shifu.doskiad.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.shifu.doskiad.model.Category;
import io.shifu.doskiad.repository.CategoryRepository;

@Service
public class CategoryServiceImpl implements CategoryService{
	
	
	private final CategoryRepository categoryRepository;
	
	@Autowired
	public CategoryServiceImpl(CategoryRepository categoryRepository) {
		this.categoryRepository =  categoryRepository;
	}
	
	@Override
	public List<Category> findAll(){
		return categoryRepository.findAll();
	}

}
