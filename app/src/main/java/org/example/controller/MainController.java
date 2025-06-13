package org.example.controller;

import org.example.dao.UserDao;
import org.example.entity.UserInfo;
import org.example.repository.UserRepository;
import org.example.service.UserDaoImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class MainController {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserDao userDao;

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
    @ResponseBody
    public String addUser(@RequestBody UserInfo userInfo){
        userRepository.save(userInfo);
        return "SAVED USER!!";
    }

    @GetMapping("/get")
    public  List<UserInfo> getAllUser(){
        return userDao.getAllUser();
    }

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
