package io.shifu.doskiad.services;


import java.util.List;
import java.util.Optional;

import io.shifu.doskiad.repository.ContactRepository;
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
    private final ContactRepository contactRepository;

    @Autowired
    public ItemServiceImpl(ItemRepository itemRepository, ContactRepository contactRepository) {
        this.itemRepository = itemRepository;
        this.contactRepository = contactRepository;
    }

    @Override
    public void save(Item item) {
        itemRepository.save(item);
        item.getContacts().forEach(f -> f.setItem(item.getId()));
        contactRepository.saveAll((item.getContacts()));
    }

    @Override
    public void update(Item item) {
        itemRepository.save(item);
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

    @Override
    public Page<Item> findItemsByIds(List<Long> ids, Integer page, Integer size, String direction, String param) {
        return itemRepository.findByIdIn(ids, PageRequest.of(
                page-1, size, Sort.Direction.valueOf(direction.toUpperCase()), param));
    }
}
