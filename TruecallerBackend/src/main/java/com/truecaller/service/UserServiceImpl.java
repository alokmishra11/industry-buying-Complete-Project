package com.truecaller.service;

import com.truecaller.model.UserRdo;
import com.truecaller.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by emp350 on 17/05/20
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<UserRdo> getUserDetails() {

        return null;
    }
}
