package com.truecaller.service;

import com.truecaller.entity.Contact;
import com.truecaller.entity.User;
import com.truecaller.entity.UserContact;
import com.truecaller.model.LoginQdo;
import com.truecaller.model.LoginRdo;
import com.truecaller.model.SignupQdo;
import com.truecaller.model.UserRdo;
import com.truecaller.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserContactService userContactService;
    @Autowired
    private ContactService contactService;
    @Autowired
    private PasswordEncoder bcryptEncoder;


    @Override
    public LoginRdo signUp(SignupQdo signupQdo) {
        User user = new User();
        user.setName(signupQdo.getName());
        user.setPassword(bcryptEncoder.encode(user.getPassword()));
        user.setEmail(signupQdo.getEmail());
        userRepository.save(user);
        return new LoginRdo(user);
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
        User user = getUserByPhone(loginQdo.getPhone());
        if (user != null && user.getPassword().equals(loginQdo.getPassword())) {
            return new LoginRdo(user);
        }
        return null;
    }

    /**
     * search user details by name or phone number
     *
     * @param search
     * @return
     * @Param user
     */
    @Override
    public List<UserRdo> getUserDetails(User user, String search) {
        List<UserContact> userContacts;
        // if search string is a phone number
        if (isPhoneNumber(search)) {
            userContacts = userContactService.searchByPhone(search);
            if (userContacts.size() > 0) {
                Contact contact = userContacts.get(0).getContact();
                // if user with the matching phone is a registered user
                if (contact != null && contact.getUser() != null) {
                    UserRdo userRdo = new UserRdo();
                    userRdo.name = contact.getUser().getName();
                    userRdo.phone = contact.getPhone();
                    userRdo.email = contact.getUser().getEmail();
                    return Collections.singletonList(userRdo);
                }
            }
            // if search string is a name
        } else {
            userContacts = userContactService.searchByName(search);
        }
        return userContacts.stream().map(x -> new UserRdo(x, user.getId())).collect(Collectors.toList());
    }

    /**
     * check if search string contains only digits(phone number)
     *
     * @param search
     * @return
     */
    private boolean isPhoneNumber(String search) {
        return search.matches("[0-9]+");
    }
}