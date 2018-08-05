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
    ResponseEntity<List<Item>> getItemsByCategoryId(
            @PathVariable("id") Long id,
            @RequestParam(value = "page", required = false, defaultValue = "1") Integer page,
            @RequestParam(value = "size", required = false, defaultValue = "12") Integer size,
            @RequestParam(value = "d", required = false, defaultValue = "desc") String direction,
            @RequestParam(value = "p", required = false, defaultValue = "date") String param,
            @RequestParam(value = "min", required = false, defaultValue = "-1") Long min,
            @RequestParam(value = "max", required = false, defaultValue = "999999999999999") Long max,
            @RequestParam(value = "title", required = false) String title) {
        Page<Item> result;
        if (title != null) {
            result = itemService.findByCategoryAndTitle(id, title, page, size, direction, param, min, max);
        } else {
            result = itemService.findByCategory(id, page, size, direction, param, min, max);
        }
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("totalFound", Long.toString(result.getTotalElements()));
        responseHeaders.set("totalPage", Integer.toString(result.getTotalPages()));
        return new ResponseEntity<>(result.getContent(), responseHeaders, HttpStatus.OK);
    }

    @RequestMapping(value = "/items/{id}", method = RequestMethod.GET)
    ResponseEntity<Item> getItemById(@PathVariable("id") Long id) {
        // отдаём статус ОК и итем, если существует или ошибку 204, если не найдено
        return itemService.findById(id).map(item -> new ResponseEntity<>(item, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NO_CONTENT));
    }

    @RequestMapping(value = "/items", method = RequestMethod.GET)
    ResponseEntity<List<Item>> getAllItems(
            @RequestParam(value = "page", required = false, defaultValue = "1") Integer page,
            @RequestParam(value = "size", required = false, defaultValue = "12") Integer size,
            @RequestParam(value = "d", required = false, defaultValue = "desc") String direction,
            @RequestParam(value = "p", required = false, defaultValue = "date") String param,
            @RequestParam(value = "min", required = false, defaultValue = "-1") Long min,
            @RequestParam(value = "max", required = false, defaultValue = "999999999999999") Long max,
            @RequestParam(value = "title", required = false) String title) {
        Page<Item> result;
        if (title != null) {
            result = itemService.findByTitle(title, page, size, direction, param, min, max);
        } else {
            result = itemService.find(page, size, direction, param, min, max);
        }
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("totalFound", Long.toString(result.getTotalElements()));
        responseHeaders.set("totalPage", Integer.toString(result.getTotalPages()));
        return new ResponseEntity<>(result.getContent(), responseHeaders, HttpStatus.OK);
    }
}
