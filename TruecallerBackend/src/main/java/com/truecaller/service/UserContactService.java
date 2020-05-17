package com.truecaller.service;

import com.truecaller.entity.UserContact;

import java.util.List;

public interface UserContactService {

    List<UserContact> searchByName(String name);

    List<UserContact> searchByPhone(String phone);
}