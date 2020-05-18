package com.truecaller.service;

import com.truecaller.entity.Contact;
import com.truecaller.entity.User;
import com.truecaller.model.LoginQdo;
import com.truecaller.model.LoginRdo;
import com.truecaller.model.SignupQdo;

public interface UserService {

    LoginRdo signUp(SignupQdo signupQdo, Contact contact) throws Exception;

    User getUserByPhone(String phone);

    LoginRdo login(LoginQdo loginQdo) throws Exception;
}