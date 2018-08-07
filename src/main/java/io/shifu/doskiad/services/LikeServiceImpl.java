package io.shifu.doskiad.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.shifu.doskiad.model.Like;
import io.shifu.doskiad.repository.LikeRepository;

@Service
public class LikeServiceImpl implements LikeService{

	private final LikeRepository likeRepository;

	@Autowired
	public LikeServiceImpl(LikeRepository likeRepository) {
		this.likeRepository = likeRepository;
	}

	@Override
	public List<Long> findItemsIdByUser(Long id){
		List<Like> likes = likeRepository.findByUser(id);
		return likes.stream().map(Like::getItem).collect(Collectors.toList());
	}

	@Override
	public Optional<Like> findLikeByUserAndItem(Long user, Long id){
		return likeRepository.findByUserAndItem(user, id);
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
