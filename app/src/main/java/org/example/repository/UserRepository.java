package org.example.repository;

import org.example.entity.UserInfo;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<UserInfo,Long> {

    UserInfo findByName(String name);
}
