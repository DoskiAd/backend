package io.shifu.doskiad.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import io.shifu.doskiad.model.Item;

public interface ItemRepository extends JpaRepository<Item, Long> {

}
