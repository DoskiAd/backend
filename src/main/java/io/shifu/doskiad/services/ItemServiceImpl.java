package io.shifu.doskiad.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
	public List<Item> findAll(){
		return itemRepository.findAll();
	}

}
