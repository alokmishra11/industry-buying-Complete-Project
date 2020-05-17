package com.truecaller.model;

import com.truecaller.entity.User;

/**
 * Created by emp350 on 17/05/20
 */
public class LoginRdo {

    public String authToken;

    public LoginRdo() {
    }

    public LoginRdo(User user) {
        this.authToken = user.getPassword();
    }
}
