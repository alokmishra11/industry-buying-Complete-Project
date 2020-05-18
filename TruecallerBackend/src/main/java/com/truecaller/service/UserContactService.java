package com.truecaller.service;

import com.truecaller.entity.User;
import com.truecaller.entity.UserContact;
import com.truecaller.model.UserRdo;

import java.util.List;

public interface UserContactService {

    List<UserContact> searchByName(String name);

    List<UserContact> searchByPhone(String phone);

    List<UserRdo> getUserDetails(User user, String search);
}