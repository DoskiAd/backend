package io.shifu.doskiad.services;

import org.springframework.data.domain.Page;

import io.shifu.doskiad.model.Item;

public interface ItemService {
	
	Page<Item> findAll(Integer page, Integer size, String direction, String param);
	Page<Item> findByCategoryId(Long id, Integer page, Integer size, String direction, String param);

}
