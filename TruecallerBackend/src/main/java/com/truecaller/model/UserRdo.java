package com.truecaller.model;

import com.truecaller.entity.User;
import com.truecaller.entity.UserContact;

public class UserRdo {

    public String name;
    public String phone;
    public String email;
    public long spamCount;

    public UserRdo() {
    }

    public UserRdo(UserContact userContact, long userId) {
        this.name = userContact.getContactName();
        this.phone = userContact.getContact().getPhone();
        User contactUser = userContact.getContact().getUser();
        if(contactUser != null && userContact.getUser().getId() == userId){
            this.email = contactUser.getEmail();
        }
        this.spamCount = userContact.getContact().getSpamCount();
    }
}