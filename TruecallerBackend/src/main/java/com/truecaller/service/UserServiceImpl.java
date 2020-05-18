package com.truecaller.service;

import com.truecaller.config.JwtTokenUtil;
import com.truecaller.entity.Contact;
import com.truecaller.entity.User;
import com.truecaller.model.LoginQdo;
import com.truecaller.model.LoginRdo;
import com.truecaller.model.SignupQdo;
import com.truecaller.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

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
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    private JwtUserDetailsService jwtUserDetailsService;


    @Override
    public LoginRdo signUp(SignupQdo signupQdo) throws Exception {
        User user = new User();
        user.setName(signupQdo.getName());
        user.setPassword(bcryptEncoder.encode(user.getPassword()));
        user.setEmail(signupQdo.getEmail());
        userRepository.save(user);
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
    public LoginRdo login(LoginQdo loginQdo) throws Exception {
        authenticate(loginQdo.getPhone(), loginQdo.getPassword());
        UserDetails userDetails = jwtUserDetailsService.loadUserByUsername(loginQdo.getPhone());
        String token = jwtTokenUtil.generateToken(userDetails);
        return new LoginRdo(token);
    }

    private void authenticate(String username, String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new Exception("User disabled", e);
        } catch (BadCredentialsException e) {
            throw new Exception("Invalid credentials", e);
        }
    }
}