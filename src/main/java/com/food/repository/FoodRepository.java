package com.food.repository;

import com.food.entity.StatusEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;

import com.food.entity.Cart;

import java.util.List;

@Component
public interface FoodRepository extends JpaRepository<Cart, Integer>{

    @Query(value = "SELECT * FROM cart " +
            "WHERE " +
            " (:name IS NULL OR LOWER(cart.name) LIKE LOWER(CONCAT('%', :name, '%'))) " +
            "AND (:status IS NULL OR LOWER(cart.status) LIKE LOWER(CONCAT('%', :status, '%'))) " +
            "AND (:type IS NULL OR LOWER(cart.type) LIKE LOWER(CONCAT('%', :type, '%')))", nativeQuery = true)
	public List<Cart> SearchCarts(String name, StatusEnum status, String type);
	
	
}
