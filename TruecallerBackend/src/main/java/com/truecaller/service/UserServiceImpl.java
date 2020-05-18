package com.truecaller.service;

import com.truecaller.common.config.JwtUtils;
import com.truecaller.entity.Contact;
import com.truecaller.entity.User;
import com.truecaller.model.LoginQdo;
import com.truecaller.model.LoginRdo;
import com.truecaller.model.SignupQdo;
import com.truecaller.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ContactService contactService;
    @Autowired
    private PasswordEncoder bcryptEncoder;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtUtils jwtUtils;
    @Autowired
    private JwtUserDetailsService jwtUserDetailsService;


    @Override
    @Transactional
    public LoginRdo signUp(SignupQdo signupQdo, Contact contact) throws Exception {
        User user = new User();
        user.setName(signupQdo.getName());
        user.setPassword(bcryptEncoder.encode(signupQdo.getPassword()));
        user.setEmail(signupQdo.getEmail());
        userRepository.save(user);
        //save user in contact
        contactService.saveContactUser(contact, user);
        // login from signup
        return loginFromSignup(signupQdo);
    }

    private LoginRdo loginFromSignup(SignupQdo signupQdo) throws Exception {
        LoginQdo loginQdo = new LoginQdo();
        loginQdo.setPhone(signupQdo.getPhone());
        loginQdo.setPassword(signupQdo.getPassword());
        return login(loginQdo);
    }

    /**
     * get user by phone number
     *
     * @param phone
     * @return
     */
    @Override
    public User getUserByPhone(String phone) {
        Contact contact = contactService.getOneByPhone(phone);
        if (contact != null)
            return contact.getUser();
        return null;
    }

    /**
     * user login
     *
     * @param loginQdo
     * @return
     */
    @Override
    public LoginRdo login(LoginQdo loginQdo) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginQdo.getPhone(), loginQdo.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);
        return new LoginRdo(jwt);
    }
}