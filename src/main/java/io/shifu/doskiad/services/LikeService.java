package io.shifu.doskiad.services;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;

import io.shifu.doskiad.model.Item;
import io.shifu.doskiad.model.Like;

public interface LikeService {
	
	Page<Item> findItemsByLikes(List<Like> likes, Integer page, Integer size, String direction, String param);
	List<Like> findLikesByUser(Long id);
	List<Long> findItemsIdByUser(Long id);
	Optional<Like> findLikeByItem(Long id);
	void add(Like like);
	void deleteByUserAndItem(Long userid, Long itemid);
	
}
