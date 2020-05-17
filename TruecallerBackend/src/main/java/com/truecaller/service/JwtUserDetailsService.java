package com.truecaller.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.truecaller.dao.UserDao;
import com.truecaller.model.DAOUser;



@Service
public class JwtUserDetailsService implements UserDetailsService {
	
@Autowired
private UserDao userDao;

@Autowired
private PasswordEncoder bcryptEncoder;

@Override
public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
	DAOUser user = userDao.findByUsername(username);
	if (user == null) {
		throw new UsernameNotFoundException("User not found with username: " + username);
	}
	return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
			new ArrayList<>());
}
}