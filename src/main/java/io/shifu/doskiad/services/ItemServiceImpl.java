package io.shifu.doskiad.services;


import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import io.shifu.doskiad.model.Item;
import io.shifu.doskiad.repository.ItemRepository;

@Service
public class ItemServiceImpl implements ItemService {

    private final ItemRepository itemRepository;

    @Autowired
    public ItemServiceImpl(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }
    
    @Override
    public Optional<Item> findById(Long id) {
    	return itemRepository.findById(id);
    }

    @Override
    public Page<Item> find(Integer page, Integer size, String direction, String param, Long min, Long max) {
        return itemRepository.findAllByPriceBetween(min, max, PageRequest.of(
                page - 1, size, Sort.Direction.valueOf(direction.toUpperCase()), param));
    }

    @Override
    public Page<Item> findByTitle(String title, Integer page, Integer size, String direction, String param, Long min, Long max) {
        return itemRepository.findAllByTitleContainingAndPriceBetween(title, min, max, PageRequest.of(
                page - 1, size, Sort.Direction.valueOf(direction.toUpperCase()), param));
    }

    @Override
    public Page<Item> findByCategory(Long id, Integer page, Integer size, String direction, String param, Long min, Long max) {
        return itemRepository.findAllByCategoryAndPriceBetween(id, min, max, PageRequest.of(
                page - 1, size, Sort.Direction.valueOf(direction.toUpperCase()), param));
    }

    @Override
    public Page<Item> findByCategoryAndTitle(Long id, String title, Integer page, Integer size, String direction, String param, Long min, Long max) {
        return itemRepository.findAllByCategoryAndTitleContainingAndPriceBetween(id, title, min, max, PageRequest.of(
                page - 1, size, Sort.Direction.valueOf(direction.toUpperCase()), param));
    }
}
