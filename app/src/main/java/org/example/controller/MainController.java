package org.example.controller;

import org.example.dao.UserDao;
import org.example.dto.UserDto;
import org.example.entity.UserInfo;
import org.example.repository.UserRepository;
import org.example.service.UserDaoImp;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class MainController {


    private UserRepository userRepository;

//    private UserDao userDao;
    @Autowired
    private UserDaoImp userDaoImp;
    @Autowired
    private ModelMapper modelMapper;

//    @Autowired
//    private UserInfo userInfo;
//
//    @PostMapping("/add")
//    public @ResponseBody  String addUSer(@RequestParam("name") String name,@RequestParam("email") String email,@RequestParam("phoneNumber") String phoneNumber){
//        UserInfo userInfo=new UserInfo();
//        userInfo.setName(name);
//        userInfo.setEmail(email);
//        userInfo.setPhoneNumber(phoneNumber);
//        userRepository.save(userInfo);
//        return "SAVED USER!!";
//    }
    @PostMapping("/add")
    public ResponseEntity<UserDto> addUser(@RequestBody UserInfo userInfo){
        userDaoImp.addUser(userInfo);
        UserDto userDto=modelMapper.map(userInfo, UserDto.class);
        return new ResponseEntity<>(userDto, HttpStatus.OK);
    }

    @GetMapping("/get/{phone_number}")
    public  ResponseEntity<UserDto> getAllUser(@PathVariable("phone_number")String number){
//        String number="1234342";
        UserDto userDto=userDaoImp.getUserByPhone(number);
        return new ResponseEntity<>(userDto,HttpStatus.OK);
    }
//    @GetMapping("/get")
//    public List<UserInfo> getAllUSer(){
//        return userDao.getAllUser();
//    }

    @DeleteMapping("/delete/{id}")
    public  String deleteUser(@PathVariable Long id){
        userRepository.deleteById(id);
        return "USER DELETED!!";
    }

    @PutMapping("/update/{id}")
    public UserInfo updateUser(@RequestBody UserInfo userInfo,@PathVariable Long id){
        return userRepository.findById(id).
                map(userInfo1 -> {userInfo1.setName(userInfo.getName());
                    userInfo1.setEmail(userInfo.getEmail());
                    userInfo1.setPhoneNumber(userInfo.getPhoneNumber());
                return userRepository.save(userInfo1);}
                ).orElseGet(()->{
                    return userRepository.save(userInfo);
                });
    }


}
