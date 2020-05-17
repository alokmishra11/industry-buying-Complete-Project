package com.TrueCallerMobileApp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.TrueCallerMobileApp.Model.UserModel;


@RestController
public class UsersController {
	
	@Autowired
	UserModel userModel;

	@PostMapping
	public String userRegisterController(UserModel userModel) {
		return null;

	}

}
