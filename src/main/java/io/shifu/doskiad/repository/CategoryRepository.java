package io.shifu.doskiad.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import io.shifu.doskiad.model.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {

}
