package com.jp.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {
	
	@Autowired
	private UserRepository userRepository;
	
	@PostMapping
	public ResponseEntity<?> createUser(@RequestBody UserEntity userDetails){
		 UserEntity savedUser = userRepository.save(userDetails);
		 return ResponseEntity.status(HttpStatus.CREATED).body(savedUser);
	}
	
	@GetMapping("/{id}")
	public UserEntity getUser(@PathVariable Integer id){
		 UserEntity user = userRepository.findById(id).orElseThrow(()->new RuntimeException("Invalid User"));
		 return user;
	}
	
	@GetMapping
	public List<UserEntity> getAllUsers(){
		return userRepository.findAll();
	}
	
	@PutMapping("/{id}")
	public UserEntity updateUser(@PathVariable Integer id, @RequestBody UserEntity user) {
		UserEntity existingUser = userRepository.findById(id).orElseThrow(()->new RuntimeException("Invalid User"));
		existingUser.setLastName(user.getLastName());
		return user;
	}
	
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@DeleteMapping("/{id}")
	public void deletUser(@PathVariable Integer id) {
		userRepository.findById(id).orElseThrow(()->new RuntimeException("Invalid User"));
		userRepository.deleteById(id);
	}
	
	
	

}
