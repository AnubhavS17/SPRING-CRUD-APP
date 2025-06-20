package org.example.service;

import org.example.dao.UserDao;
import org.example.entity.UserInfo;
import org.example.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.StreamSupport;

@Service
@Component
public class UserDaoImp implements UserDao {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserInfo getUser(String name) {
        return userRepository.findByName(name);
    }

    @Override
    public List<UserInfo> getAllUser() {
        Iterable<UserInfo> iterable = userRepository.findAll();

        List<UserInfo> list = StreamSupport
                .stream(iterable.spliterator(), false)
                .toList();
        return list;
    }
}
