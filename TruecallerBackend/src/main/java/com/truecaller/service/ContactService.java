package com.truecaller.service;

import com.truecaller.entity.Contact;

public interface ContactService {

    boolean markAsSpam(String phone);

    Contact getOneByPhone(String phone);
}