package com.truecaller.repository;

import com.truecaller.entity.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Created by emp350 on 17/05/20
 */
@Repository
public interface ContactRepository extends JpaRepository<Contact, Long> {

    @Query("select c from Contact as c where c.phone =:phone")
    Contact findOneByPhone(@Param("phone") String phone);
}
