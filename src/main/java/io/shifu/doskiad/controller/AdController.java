package io.shifu.doskiad.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.shifu.doskiad.model.Category;
import io.shifu.doskiad.model.Item;
import io.shifu.doskiad.services.CategoryService;
import io.shifu.doskiad.services.ItemService;

@RestController
public class AdController {
	
	private final CategoryService categoryService;
	private final ItemService itemService;
	
	@Autowired
	public AdController(CategoryService categoryService, ItemService itemService) {
		this.categoryService = categoryService;
		this.itemService = itemService;
	}
	
	@RequestMapping(value = "/api/v1/categories", method = RequestMethod.GET)
	ResponseEntity<List<Category>> getAllCategories() {
		return new ResponseEntity<>(categoryService.findAll(), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/api/v1/items", method = RequestMethod.GET)
	ResponseEntity<List<Item>> getAllItems() {
		return new ResponseEntity<>(itemService.findAll(), HttpStatus.OK);
	}

}
