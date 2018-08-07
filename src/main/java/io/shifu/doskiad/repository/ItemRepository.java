package io.shifu.doskiad.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import io.shifu.doskiad.model.Item;

public interface ItemRepository extends JpaRepository<Item, Long> {
	
	Page<Item> findByIdIn(List<Long> ids, Pageable p);

    Page<Item> findAllByPriceBetween(Long min, Long max, Pageable pageable);

    Page<Item> findAllByTitleContainingAndPriceBetween(String title, Long min, Long max, Pageable pageable);

    Page<Item> findAllByCategoryAndPriceBetween(Long id, Long min, Long max, Pageable pageable);

    Page<Item> findAllByCategoryAndTitleContainingAndPriceBetween(Long id, String title, Long min, Long max, Pageable pageable);
}
