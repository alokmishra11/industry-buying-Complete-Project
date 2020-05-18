package com.truecaller.entity;

import javax.persistence.*;
import javax.persistence.GenerationType;

@Entity
@Table(name = "contact")
public class Contact implements java.io.Serializable {

    private Long id;
    private String phone;
    private long spamCount;
    private User user;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "phone", nullable = false)
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Column(name = "spamcount")
    public long getSpamCount() {
        return spamCount;
    }

    public void setSpamCount(long spamCount) {
        this.spamCount = spamCount;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user")
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
