package io.shifu.doskiad.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import io.shifu.doskiad.model.Item;
import io.shifu.doskiad.repository.ItemRepository;

@Service
public class ItemServiceImpl implements ItemService{

private final ItemRepository itemRepository;
	
 
	@Autowired
	public ItemServiceImpl(ItemRepository itemRepository) {
		this.itemRepository =  itemRepository;
	}	
	
	@Override
	public Page<Item> findAll(Integer page, Integer size, String direction, String param){
		return itemRepository.findAll(PageRequest.of(
				page-1, size, Sort.Direction.valueOf(direction.toUpperCase()), param));
	}
	
	@Override
	public Page<Item> findByCategoryId(Long id, Integer page, Integer size, String direction, String param){
		return itemRepository.findByCategory(id, PageRequest.of(
				page-1, size, Sort.Direction.valueOf(direction.toUpperCase()), param));
	}


}
