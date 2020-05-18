package com.truecaller.service;

import com.truecaller.entity.Contact;
import com.truecaller.entity.User;
import com.truecaller.repository.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ContactServiceImpl implements ContactService {

    @Autowired
    private ContactRepository contactRepository;

    /**
     * mark contact as spam
     *
     * @param phone
     * @return
     */
    @Override
    public boolean markAsSpam(String phone) {
        Contact contact = contactRepository.findOneByPhone(phone);
        if (contact != null) {
            long spamCount = contact.getSpamCount();
            contact.setSpamCount(spamCount + 1);
            contactRepository.save(contact);
            return true;
        }
        return false;
    }

    /**
     * get contract by phone number
     *
     * @param phone
     * @return
     */
    @Override
    public Contact getOneByPhone(String phone) {
        return contactRepository.findOneByPhone(phone);
    }

    /**
     * save contact user
     *
     * @param contact
     * @param user
     */
    @Override
    public void saveContactUser(Contact contact, User user) {
        contact.setUser(user);
        contactRepository.save(contact);
    }
}
