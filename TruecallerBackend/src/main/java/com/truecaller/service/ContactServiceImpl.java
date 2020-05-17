package com.truecaller.service;

import com.truecaller.entity.Contact;
import com.truecaller.repository.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by emp350 on 17/05/20
 */
@Service
public class ContactServiceImpl implements ContactService {

    @Autowired
    private ContactRepository contactRepository;

    /**
     * mark contact as spam
     *
     * @param phoneNumber
     * @return
     */
    @Override
    public boolean markAsSpam(String phoneNumber) {
        Contact contact = contactRepository.findOneByPhoneNumber(phoneNumber);
        if (contact != null) {
            long spamCount = contact.getSpamCount();
            contact.setSpamCount(spamCount + 1);
            contactRepository.save(contact);
            return true;
        }
        return false;
    }
}
