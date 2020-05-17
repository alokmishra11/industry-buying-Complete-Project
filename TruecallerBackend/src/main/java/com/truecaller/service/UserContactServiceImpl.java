package com.truecaller.service;

import com.truecaller.entity.UserContact;
import com.truecaller.repository.UserContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserContactServiceImpl implements UserContactService {

    @Autowired
    private UserContactRepository userContactRepository;


    @Override
    public List<UserContact> searchByName(String name) {
        return userContactRepository.findByName(name);
    }

    @Override
    public List<UserContact> searchByPhone(String phone) {
        return userContactRepository.findByPhone(phone);
    }
}