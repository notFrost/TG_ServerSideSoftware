package com.opense.traininggain.service;


import com.opense.traininggain.domain.model.Specialist;
import com.opense.traininggain.domain.model.User;
import com.opense.traininggain.domain.repository.UserRepository;
import com.opense.traininggain.domain.service.UserService;
import com.opense.traininggain.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    @Override
    public Page<User> getAllUsers(Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    @Override
    public User getUserById(Long userId) {
        return userRepository.findById(userId).orElseThrow(()->new ResourceNotFoundException("User","Id",userId));
    }

    @Override
    public User createUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public User updateUser(Long userId, User userDetails) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User", "Id", userId));
        user.setName(userDetails.getName());
        user.setLastname(userDetails.getLastname());
        user.setBirth(userDetails.getBirth());
        user.setAddress(userDetails.getAddress());
        user.setPhone(userDetails.getPhone());
        user.setAge(userDetails.getAge());
        user.setEmail(userDetails.getEmail());
        user.setGender(userDetails.getGender());
        user.setPassword(userDetails.getPassword());
        user.setCountry(userDetails.getCountry());

        return userRepository.save(user);
    }

    @Override
    public ResponseEntity<?> deleteUser(Long userId) {
        User user = userRepository.findById(userId).
                orElseThrow(()-> new ResourceNotFoundException("Specialist", "Id", userId));
        userRepository.delete(user);
        return ResponseEntity.ok().build();

    }
}
