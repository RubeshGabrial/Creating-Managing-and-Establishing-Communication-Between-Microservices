package com.bej.muzixservice.proxy;


import com.bej.muzixservice.domain.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name="user-authentication-service",url="localhost:8083")
public interface UserProxy {

    @PostMapping("/api/v1/save")
    public ResponseEntity<?> saveUser(@RequestBody User user);
}
