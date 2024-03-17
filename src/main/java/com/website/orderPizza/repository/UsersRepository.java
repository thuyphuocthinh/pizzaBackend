package com.website.orderPizza.repository;

import com.website.orderPizza.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersRepository extends JpaRepository<Users, Integer> {
    Users findByEmail(String email);
    Users findByResetPasswordToken(String token);

}
