package com.example.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Users;
import com.example.demo.repositories.UserRepository;

@Service
public class UserServiceImplementation implements UserServices{
@Autowired
UserRepository rs;

@Override
public String getAllUser(Users user) {
	rs.save(user);
	return "user added sucessfully";
}

@Override
public boolean emailExists(String email) {
	if(rs.findByEmail(email)==null) {
		return false;
	}
		else {
	
	return true;
}
}
@Override
public boolean validateUser(String email, String password) {
	Users user=rs.findByEmail(email);
	String pass=user.getPassword();
	if(password.equals(pass)) {
		return true;
	}else {
	return false;
}
}
@Override
public String getRole(String email) {
	Users user=rs.findByEmail(email);
	return user.getRole();
}

@Override
public Users getUser(String email) {
	
	return rs.findByEmail(email);
}

@Override
public void updateUser(Users user) {
	rs.save(user);
	
}
}
