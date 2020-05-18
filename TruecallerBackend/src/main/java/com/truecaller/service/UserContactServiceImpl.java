package com.truecaller.service;

import com.truecaller.entity.Contact;
import com.truecaller.entity.User;
import com.truecaller.entity.UserContact;
import com.truecaller.model.UserRdo;
import com.truecaller.repository.UserContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserContactServiceImpl implements UserContactService {

    @Autowired
    private UserContactRepository userContactRepository;


    @Override
    public List<UserContact> searchByName(String name) {
        String likeName = "%"+name+"%";
        return userContactRepository.findByName(likeName, name);
    }

    @Override
    public List<UserContact> searchByPhone(String phone) {
        return userContactRepository.findByPhone(phone);
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
            userContacts = searchByPhone(search);
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
            userContacts = searchByName(search);
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