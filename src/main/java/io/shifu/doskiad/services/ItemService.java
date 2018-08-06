package io.shifu.doskiad.services;

import java.util.Optional;

import org.springframework.data.domain.Page;

import io.shifu.doskiad.model.Item;

public interface ItemService {

    void save(Item item);

    void update(Item item);
	
	Optional<Item> findById(Long id);

    Page<Item> find(Integer page, Integer size, String direction, String param, Long min, Long max);

    Page<Item> findByTitle(String title, Integer page, Integer size, String direction, String param, Long min, Long max);

    Page<Item> findByCategory(Long id, Integer page, Integer size, String direction, String param, Long min, Long max);

    Page<Item> findByCategoryAndTitle(Long id, String title, Integer page, Integer size, String direction, String param, Long min, Long max);
}
