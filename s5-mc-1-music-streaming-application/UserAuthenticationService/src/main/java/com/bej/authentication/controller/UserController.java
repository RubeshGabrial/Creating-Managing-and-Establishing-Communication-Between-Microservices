package com.bej.authentication.controller;

import com.bej.authentication.exception.InvalidCredentialsException;
import com.bej.authentication.exception.UserAlreadyExistsException;
import com.bej.authentication.security.SecurityTokenGenerator;
import com.bej.authentication.service.IUserService;
import com.bej.authentication.domain.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class UserController {
    //Autowire the dependencies for UserService and SecurityTokenGenerator
    private final IUserService userService;
    private final SecurityTokenGenerator securityTokenGenerator;

    public UserController(IUserService userService, SecurityTokenGenerator securityTokenGenerator) {
        this.userService = userService;
        this.securityTokenGenerator = securityTokenGenerator;
    }


    @PostMapping("/save")
    public ResponseEntity<?> saveUser(@RequestBody User user) {
        // Write the logic to save a user,
        // return 201 status if user is saved else 500 status
        try{
            return new ResponseEntity<>(userService.saveUser(user), HttpStatus.CREATED);
        }catch (UserAlreadyExistsException e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.CONFLICT);
        }

    }
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User user)
    {
        // Generate the token on login,
        // return 200 status if user is saved else 500 status
        ResponseEntity responseEntity;
        try {
            User fetchUser=userService.getUserByUserIdAndPassword(user.getUserId(), user.getPassword());
            String token=securityTokenGenerator.createToken(fetchUser);
            responseEntity=new ResponseEntity<>(token,HttpStatus.OK);
        }catch (InvalidCredentialsException e){
            responseEntity= new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }
}
