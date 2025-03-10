package edu.poly.assignment_java5.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.poly.assignment_java5.model.Users;

public interface UsersRepository extends JpaRepository<Users, String> {
	Optional<Users> findBySdt(String sdt);

	
	

}