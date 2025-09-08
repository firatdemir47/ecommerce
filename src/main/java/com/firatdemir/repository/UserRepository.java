package com.firatdemir.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.firatdemir.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

}
