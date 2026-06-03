package com.example.shivamart.repository;

import com.example.shivamart.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}