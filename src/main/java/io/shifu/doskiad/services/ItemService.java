package io.shifu.doskiad.services;

import java.util.Optional;

import org.springframework.data.domain.Page;

import io.shifu.doskiad.model.Item;

public interface ItemService {
	
	Optional<Item> findById(Long id);

    Page<Item> find(Integer page, Integer size, String direction, String param);

    Page<Item> findByTitle(String title, Integer page, Integer size, String direction, String param);

    Page<Item> findByCategory(Long id, Integer page, Integer size, String direction, String param);

    Page<Item> findByCategoryAndTitle(Long id, String title, Integer page, Integer size, String direction, String param);
}
