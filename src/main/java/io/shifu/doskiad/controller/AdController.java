package io.shifu.doskiad.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.shifu.doskiad.model.Category;
import io.shifu.doskiad.model.Item;
import io.shifu.doskiad.services.CategoryService;
import io.shifu.doskiad.services.ItemService;

@RestController
@RequestMapping("/doskiad")
public class AdController {
	
	private final CategoryService categoryService;
	private final ItemService itemService;
	
	@Autowired
	public AdController(CategoryService categoryService, ItemService itemService) {
		this.categoryService = categoryService;
		this.itemService = itemService;
	}
	
	@RequestMapping(value = "/categories", method = RequestMethod.GET)
	ResponseEntity<List<Category>> getAllCategories() {
		return new ResponseEntity<>(categoryService.findAll(), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/category/{id}", method = RequestMethod.GET)
	ResponseEntity<List<Item>> getItemsByCategoryId(@PathVariable("id") Long id,
			@RequestParam(value="page", required=false) Integer page,
			@RequestParam(value="size", required=false) Integer size,
			@RequestParam(value="d", required=false) String direction,
			@RequestParam(value="p", required=false) String param){
		Page<Item> result = itemService.findByCategoryId(id,
					        	page == null ? 1 : page,
					        	size == null ? 12 : size,
							    direction == null ? "desc" : direction,
							    param == null ? "date" : param);
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.set("x-total-found", Long.toString(result.getTotalElements()));
		responseHeaders.set("x-total-pages", Integer.toString(result.getTotalPages()));        
		return new ResponseEntity<>(result.getContent(), responseHeaders, HttpStatus.OK);		
	}
	
	@RequestMapping(value = "/items", method = RequestMethod.GET)
	ResponseEntity<List<Item>> getAllItems(
			@RequestParam(value="page", required=false) Integer page,
			@RequestParam(value="size", required=false) Integer size,
			@RequestParam(value="d", required=false) String direction,
			@RequestParam(value="p", required=false) String param) {
		Page<Item> result = itemService.findAll(
				        	page == null ? 1 : page,
				        	size == null ? 12 : size,
						    direction == null ? "desc" : direction,
						    param == null ? "date" : param);
		HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("x-total-found", Long.toString(result.getTotalElements()));
        responseHeaders.set("x-total-pages", Integer.toString(result.getTotalPages()));        
        return new ResponseEntity<>(result.getContent(), responseHeaders, HttpStatus.OK);		
	}

}
