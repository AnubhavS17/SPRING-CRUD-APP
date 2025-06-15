package org.example.dao;

import org.apache.catalina.User;
import org.example.dto.UserDto;
import org.example.entity.UserInfo;

import java.util.List;

public interface UserDao {


    public List<UserInfo> getAllUser();
    public UserInfo getUser(String name);
    UserInfo addUser(UserInfo userInfo);

}
