package com.website.orderPizza.repository;

import com.website.orderPizza.entity.ProductGroup;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<ProductGroup, Integer> {
}
