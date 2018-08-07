package io.shifu.doskiad.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import io.shifu.doskiad.model.Like;

public interface LikeRepository extends JpaRepository<Like, Long>{
	
    List<Like> findByUser(Long id);

	Optional<Like> findByUserAndItem(Long userid, Long itemid);

	void deleteByUserAndItem(Long userid, Long itemid);

}
