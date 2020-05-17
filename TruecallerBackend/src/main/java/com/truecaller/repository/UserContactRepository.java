package com.truecaller.repository;

import com.truecaller.entity.UserContact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by emp350 on 17/05/20
 */
@Repository
public interface UserContactRepository extends JpaRepository<UserContact, Long> {
}
