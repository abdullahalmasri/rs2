package com.company.labeling.dao.sql;

import com.company.labeling.dao.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepo extends JpaRepository<UserEntity,Integer> {
    UserEntity findUserEntitiesByUsername(String name);

}
