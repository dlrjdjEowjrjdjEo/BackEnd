package com.medic.userservice.service;

import com.medic.userservice.dto.UserDto;
import com.medic.userservice.jpa.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

    UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // 회원 등록
    @Transactional
    public Boolean signup(String email, String name, int age, String gender) {
        UserDto user = userRepository.findOneByEmail(email);
        if (user == null) {
            UserDto userinfo = UserDto.builder().email(email).name(name).age(age).gender(gender).build();
            userRepository.save(userinfo);
            return true;
        }
        return false;
    }

    @Transactional
    public UserDto findOneByUserId(int userId) throws Exception {
        return userRepository.findOneByUserId(userId);
    }
}
