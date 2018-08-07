package io.shifu.doskiad.services;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;

import io.shifu.doskiad.model.Item;
import io.shifu.doskiad.model.Like;

public interface LikeService {

    List<Long> findItemsIdByUser(Long id);

    Optional<Like> findLikeByUserAndItem(Long userid, Long id);

    void add(Like like);

    void deleteByUserAndItem(Long userid, Long itemid);

}
