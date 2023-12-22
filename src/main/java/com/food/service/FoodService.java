package com.food.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.food.entity.Customer;
import com.food.repository.FoodRepository;

@Service
public class FoodService {

	@Autowired
	private FoodRepository foodRepository;
	
	//get All Order
	
	public List<Customer> getAllList(){
		List<Customer> list = (List<Customer>)foodRepository.findAll();
		return list;
	}
	
	// delete Order
	
	public void deletefood(int fid) {
		foodRepository.deleteById(fid);
	}
	
	//Post Order
	
	public Customer addfood(Customer order) {
		Customer result = foodRepository.save(order);
		return result;
	}
	
	//Update Order
	
	public Customer updatefood(Customer customer, int customerID) {
		customer.setId(customerID);
		return foodRepository.save(customer);
	}
}

