package com.truecaller.repository;

import com.truecaller.entity.UserContact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserContactRepository extends JpaRepository<UserContact, Long> {

    @Query(value = "select * from usercontact where contactname like :likeName order by locate(:name, contactname)", nativeQuery = true)
    List<UserContact> findByName(@Param("likeName") String likeName, @Param("name") String name);

    @Query("select uc from UserContact as uc where uc.contact.phone =:phone")
    List<UserContact> findByPhone(@Param("phone") String phone);

}
