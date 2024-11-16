package com.food.controller;
import java.util.List;

import com.food.entity.Cart;
import com.food.entity.StatusEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.food.repository.FoodRepository;
import com.food.service.FoodService;


@RestController
public class FoodController {
	@Autowired
	public FoodService foodService;
	@Autowired
	public FoodRepository foodRepository;
	@GetMapping("/cart")
	public ResponseEntity getAllCart() {

		return foodService.getAllCart();
	}
	@PostMapping("/addcart")
	public ResponseEntity addCart(@RequestBody Cart cart) {
		ResponseEntity c = this.foodService.addCart(cart);
		return c;
	}
	@PutMapping("/updatecart/{id}")
	public ResponseEntity updateCart(@RequestBody Cart cart, @PathVariable int id) {
		return foodService.updateCart(cart,id);
	}

	@DeleteMapping("/deletecart/{id}")
	public ResponseEntity deleteCart(@PathVariable int id) {
		return foodService.deleteCart(id);
	}
	@GetMapping("/pagination")
	public ResponseEntity<List<Cart>> getCartPagination(@RequestParam int page,@RequestParam int size){

		Pageable pageable = PageRequest.of(page,size);
		List<Cart> list = foodRepository.findAll(pageable).getContent();
		return ResponseEntity.ok(list);
	}

	@GetMapping("/search")
	public ResponseEntity <List<Cart>> searchCarts(
			@RequestParam(required = false) String name,
			@RequestParam(required = false) StatusEnum status,
			@RequestParam(required = false) String type){

		return foodService.searchCarts(name, status, type);
	}
}