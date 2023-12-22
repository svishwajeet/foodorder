package com.food.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

import com.food.entity.Customer;

@Component
public interface FoodRepository extends CrudRepository<Customer, Integer>{
	
	
	
}
