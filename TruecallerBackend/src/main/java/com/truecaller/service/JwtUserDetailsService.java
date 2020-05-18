package com.truecaller.service;

import com.truecaller.entity.Contact;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class JwtUserDetailsService implements UserDetailsService {

    @Autowired
    private ContactService contactService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Contact contact = contactService.getOneByPhone(username);
        if (contact == null || contact.getUser() == null) {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
        return new org.springframework.security.core.userdetails.User(contact.getPhone(), contact.getUser().getPassword(),
                new ArrayList<>());
    }
}