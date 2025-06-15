package org.example.repository;

import org.example.entity.UserInfo;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserRepository extends CrudRepository<UserInfo,Long> {

    UserInfo findByName(String name);
    UserInfo findByPhoneNumber(String number);
//    List<UserInfo> getAllUser();
//    UserInfo getUser(String name);
}
