package com.truecaller.entity;

import javax.persistence.*;
import javax.persistence.GenerationType;

@Entity
@Table(name = "contact")
public class Contact implements java.io.Serializable {

    private Long id;
    private String phoneNumber;
    private long spamCount;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "phonenumber", nullable = false)
    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Column(name = "spamcount")
    public long getSpamCount() {
        return spamCount;
    }

    public void setSpamCount(long spamCount) {
        this.spamCount = spamCount;
    }
}
