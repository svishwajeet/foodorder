package com.food.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.food.entity.Customer;
import com.food.service.FoodService;

@RestController

public class FoodController {

	@Autowired
	public FoodService foodService;
	
	@GetMapping("/cart")
	public List<Customer> getAllList(){
		return this.foodService.getAllList();
	}
	
	@DeleteMapping("/customer/{id}")
	public void deleteFood(@PathVariable("id")int id) {	
		foodService.deletefood(id);
	}
	
	@PostMapping("/food")
	public Customer addfood(@RequestBody Customer customer) {
		Customer c = this.foodService.addfood(customer);
		System.out.println("Ordered");
		return c;
	}
	
	@PutMapping("/food/{foodId}")
	public Customer updatefood(@RequestBody Customer customer, @PathVariable("foodId")int foodId) {
		return foodService.updatefood(customer,foodId);
		
	}
	
}
