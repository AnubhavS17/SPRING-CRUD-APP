package org.example.service;

import org.example.dao.UserDao;
import org.example.dto.UserDto;
import org.example.entity.UserInfo;
import org.example.repository.UserRepository;
import org.modelmapper.ModelMapper;
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
    @Autowired
    private ModelMapper modelMapper;

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
    @Override
    public UserInfo addUser(UserInfo userInfo){
        return userRepository.save(userInfo);
    }

    public UserDto getUserByPhone(String number){
        UserInfo userInfo=userRepository.findByPhoneNumber(number);
        UserDto userDto=modelMapper.map(userInfo, UserDto.class);
        return userDto;
    }
}
