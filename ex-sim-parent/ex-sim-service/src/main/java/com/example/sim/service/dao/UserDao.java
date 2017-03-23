package com.example.sim.service.dao;

import java.util.List;

import com.example.sim.service.model.User;


public interface UserDao {

	List<User> findAll();
}
