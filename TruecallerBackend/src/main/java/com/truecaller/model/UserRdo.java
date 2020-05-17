package com.truecaller.model;

import com.truecaller.entity.User;

/**
 * Created by emp350 on 17/05/20
 */
public class UserRdo {

    public long id;
    public String name;

    public UserRdo(User user){
        this.id = user.getId();
        this.name = user.getName();
    }
}
