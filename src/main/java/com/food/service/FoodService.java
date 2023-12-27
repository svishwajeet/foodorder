package com.food.service;
import java.util.List;
import com.food.entity.Error;
import com.food.entity.StatusEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import java.util.Optional;
import com.food.entity.Cart;
import com.food.repository.FoodRepository;


@Service
public class FoodService {
	@Autowired
	private FoodRepository foodRepository;

//get All Order
	public ResponseEntity getAllCart() {
		try {
			List<Cart> carts = (List<Cart>) foodRepository.findAll();
			if(carts.isEmpty()) {
				Error error = Error.builder().code(HttpStatus.NOT_FOUND.getReasonPhrase()).message("Sorry data NOT FOUND").build();

				return new ResponseEntity<>(error,HttpStatus.NOT_FOUND);
			}return new ResponseEntity<>(carts,HttpStatus.OK);
		}catch(Exception e) {
			e.printStackTrace();
			Error error =Error.builder().code(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase()).message("Some Occured Plese Resolve it!!").build();
			return new ResponseEntity<>(error,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	// Add to Cart
	public ResponseEntity addCart(Cart cart) {
		try {
			if(ObjectUtils.isEmpty(cart.getName())) {
				Error error = Error.builder().code(HttpStatus.BAD_REQUEST.getReasonPhrase()).message("Sorry data is Null").build();

				return new ResponseEntity<>(error,HttpStatus.BAD_REQUEST);
			}Cart saveCart = foodRepository.save(cart);
			return new ResponseEntity<>(saveCart,HttpStatus.OK);
		}catch(Exception e) {
			e.printStackTrace();
			Error error = Error.builder().code(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase()).message("Some Error Occured Plese Resolve it!!").build();

			return new ResponseEntity<>(error,HttpStatus.INTERNAL_SERVER_ERROR);

		}
	}
	// Update Cart
	public ResponseEntity updateCart(Cart cart,int id) {
		try {
			Optional<Cart> cartById = foodRepository.findById(id);
			if(cartById.isEmpty()){
				Error error = Error.builder().code(HttpStatus.NOT_FOUND.getReasonPhrase()).message("Sorry data is Null").build();

				return new ResponseEntity<>(error,HttpStatus.NOT_FOUND);
			}cart.setId(id);
			Cart response = foodRepository.save(cart);
			return new ResponseEntity<>(response,HttpStatus.OK);
		}catch(Exception e) {
			e.printStackTrace();

			Error error = Error.builder().code(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase()).message("Some Error Occured Plese Resolve it!!").build();

			return new ResponseEntity<>(error,HttpStatus.INTERNAL_SERVER_ERROR);

		}
	}
	//Delete Cart
	public ResponseEntity deleteCart(int id) {
		try {
			Optional<Cart> cart = foodRepository.findById(id);
			if(cart.isEmpty()) {
				Error error = Error.builder().code(HttpStatus.BAD_REQUEST.getReasonPhrase()).message("Sorry data is Null").build();
				return new ResponseEntity<>(error,HttpStatus.BAD_REQUEST);
			}foodRepository.deleteById(id);
			return new ResponseEntity<>(HttpStatus.OK);
		}catch(Exception e) {
			e.printStackTrace();
			Error error = Error.builder().code(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase()).message("Some Error Occured Plese Resolve it!!").build();

			return new ResponseEntity<>(error,HttpStatus.INTERNAL_SERVER_ERROR);

		}
	}

	public ResponseEntity searchCarts(String name, StatusEnum status,String type) {

		List<Cart> carts = foodRepository.SearchCarts(name, status,type);
		return ResponseEntity.ok(carts);
	}

}