package io.shifu.doskiad.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import io.shifu.doskiad.model.Item;
import io.shifu.doskiad.model.Like;
import io.shifu.doskiad.repository.ItemRepository;
import io.shifu.doskiad.repository.LikeRepository;

@Service
public class LikeServiceImpl implements LikeService{
	
	private final LikeRepository likeRepository;
	private final ItemRepository itemRepository;
	
	@Autowired
	public LikeServiceImpl(LikeRepository likeRepository, ItemRepository itemRepository) {
		this.likeRepository = likeRepository;
		this.itemRepository = itemRepository;
	}
	
	@Override
	public Page<Item> findItemsByLikes(List<Like> likes, Integer page, 
			Integer size, String direction, String param){
		List<Long> ids = new ArrayList<>();
		likes.forEach(like -> ids.add(like.getItem()));
		Page<Item> items = itemRepository.findByIdIn(ids, PageRequest.of(
				page-1, size, Sort.Direction.valueOf(direction.toUpperCase()), param));
		return items;
	}
	
	@Override
	public List<Like> findLikesByUser(Long id){
		return likeRepository.findByUser(id);
	}
	
	@Override
	public List<Long> findItemsIdByUser(Long id){
		List<Long> ids = new ArrayList<>();
		List<Like> likes = likeRepository.findByUser(id);
		likes.forEach(like -> ids.add(like.getItem()));
		return ids;
	}	
	
	@Override
	public Optional<Like> findLikeByItem(Long id){
		return likeRepository.findByItem(id);
	}
	
	@Override
	public void add(Like like) {
		likeRepository.save(like);
	}
	
	@Override 
	@Transactional
	public void deleteByUserAndItem(Long userid, Long itemid) {
		likeRepository.deleteByUserAndItem(userid, itemid);
	}

}
