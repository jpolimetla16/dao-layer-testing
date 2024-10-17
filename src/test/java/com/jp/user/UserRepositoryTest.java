package com.jp.user;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class UserRepositoryTest {

	@Autowired
	private UserRepository userRepository;
	
	private UserEntity userEntity;
	
	@BeforeEach
	void init() {
		userEntity = new UserEntity("Janardhan","Polimetla");
	}
	
	
	@Rollback(value = false)
	@Order(1)
	@Test
	void testCreateEmployee() {
		UserEntity savedUser = userRepository.save(userEntity);
		assertThat(savedUser.getId()).isGreaterThan(0);
	}
	
	@Order(2)
	@Test
	void testGetUser() {
		UserEntity existingUser = userRepository.findById(1).get();
		assertThat(existingUser.getId()).isEqualTo(1);
   }
	
	@Order(3)
	@Test
	void testGetAllUsers() {
		List<UserEntity> list = userRepository.findAll();
		assertThat(list.size()).isGreaterThan(0);
   }
	
	@Rollback(value = false)
	@Order(4)
	@Test
	void testUpdateUser() {
		UserEntity existingUser = userRepository.findById(1).get();
		existingUser.setLastName("Polimetla M");
		UserEntity updatedUser = userRepository.save(existingUser);
		assertThat(updatedUser.getLastName()).isEqualTo("Polimetla M");
   }
	
	@Rollback(value = false)
	@Order(5)
	@Test
	void testDelteUser() {
		userRepository.deleteById(1);
		Optional<UserEntity> optEmployee = userRepository.findById(1);
		assertThat(optEmployee).isEmpty();
		
   }
	

}
