package com.truecaller.service;

import com.truecaller.entity.User;
import com.truecaller.model.LoginQdo;
import com.truecaller.model.LoginRdo;
import com.truecaller.model.SignupQdo;
import com.truecaller.model.UserRdo;

import java.util.List;

public interface UserService {

    LoginRdo signUp(SignupQdo signupQdo);

    User getUserByPhone(String phone);

    LoginRdo login(LoginQdo loginQdo);

    List<UserRdo> getUserDetails(User user, String search);
}