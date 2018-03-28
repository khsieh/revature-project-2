package com.revature.warlockzone.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.revature.warlockzone.beans.PasswordToken;

public interface PasswordTokenDAO extends JpaRepository<PasswordToken, Integer> {

	PasswordToken findByToken(String token);

}
