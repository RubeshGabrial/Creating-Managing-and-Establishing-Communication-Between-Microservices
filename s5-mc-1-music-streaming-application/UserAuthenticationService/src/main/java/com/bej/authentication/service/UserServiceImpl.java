package com.bej.authentication.service;

import com.bej.authentication.domain.User;
import com.bej.authentication.exception.UserAlreadyExistsException;
import com.bej.authentication.exception.InvalidCredentialsException;
import com.bej.authentication.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements IUserService {

    // Autowire the UserRepository using constructor autowiring
    private final UserRepository userRepository;
    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    @Override
    public User saveUser(User user) throws UserAlreadyExistsException {
        //save the user in the db
        Optional<User>optionalUser=userRepository.findById(user.getUserId());
        if (optionalUser.isPresent()){
            throw new UserAlreadyExistsException("User already exist");
        }
        else {
            return userRepository.save(user);
        }
    }

    @Override
    public User getUserByUserIdAndPassword(String userId, String password) throws InvalidCredentialsException {
        // Validate for wrong credentials
        User user = userRepository.findByUserIdAndPassword(userId, password);
        if (user != null && user.getPassword().equals(password)) {
            return user;
        } else {
            throw new InvalidCredentialsException("Invalid credentials");
        }
    }

}
