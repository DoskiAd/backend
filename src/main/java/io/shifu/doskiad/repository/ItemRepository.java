package io.shifu.doskiad.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import io.shifu.doskiad.model.Item;

public interface ItemRepository extends JpaRepository<Item, Long> {
	
	Page<Item> findByCategory(Long id, Pageable p);

}
