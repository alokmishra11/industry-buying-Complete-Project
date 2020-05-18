package com.truecaller.service;

import com.truecaller.entity.Contact;
import com.truecaller.entity.User;

public interface ContactService {

    boolean markAsSpam(String phone);

    Contact getOneByPhone(String phone);

    void saveContactUser(Contact contact, User user);
}