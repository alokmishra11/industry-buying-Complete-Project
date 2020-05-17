package com.truecaller.service;

import com.truecaller.entity.User;
import com.truecaller.model.LoginQdo;
import com.truecaller.model.LoginRdo;
import com.truecaller.model.SignupQdo;
import com.truecaller.model.UserRdo;
import com.truecaller.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
	private PasswordEncoder bcryptEncoder;
    
    @Override
    public LoginRdo signUp(SignupQdo signupQdo) {
    	
    	User user = new User();
    	user.setName(signupQdo.getName());
    	user.setPassword(bcryptEncoder.encode(user.getPassword()));
    	user.setContact(null);
    	user.setEmail(signupQdo.getEmail());
    	userRepository.save(user);
        return new LoginRdo(user);
    }

    /**
     * get user by phone number
     *
     * @param phoneNumber
     * @return
     */
    @Override
    public User getUserByPhone(String phoneNumber){
        return userRepository.findByPhoneNumber(phoneNumber);
    }

    /**
     * user login
     *
     * @param loginQdo
     * @return
     */
    @Override
    public LoginRdo login(LoginQdo loginQdo) {
        User user = getUserByPhone(loginQdo.getPhone());
        if(user != null && user.getPassword().equals(loginQdo.getPassword())){
            return new LoginRdo(user);
        }
        return null;
    }

    @Override
    public List<UserRdo> getUserDetails(String search) {
        List<User> all = userRepository.findAll();
        return all.stream().map(UserRdo::new).collect(Collectors.toList());
    }
}
