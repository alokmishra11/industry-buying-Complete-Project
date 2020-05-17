package com.truecaller.repository;

import com.truecaller.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Created by emp350 on 17/05/20
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

}