package com.truecaller.model;

import javax.validation.constraints.NotNull;

/**
 * Created by emp350 on 17/05/20
 */
public class LoginQdo {

    @NotNull
    private String phone;
    @NotNull
    private String password;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
