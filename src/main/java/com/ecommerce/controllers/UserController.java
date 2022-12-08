package com.ecommerce.controllers;

import com.ecommerce.dto.user.UserDetailDto;
import com.ecommerce.logic.UserLogic;
import com.ecommerce.models.User;
import com.ecommerce.response.AuthResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/user")
public class UserController {
    private final UserLogic userLogic;

    public UserController(UserLogic userLogic) {
        this.userLogic = userLogic;
    }

    @RequestMapping(path = "/get-by-username", method = RequestMethod.POST)
    public ResponseEntity<UserDetailDto> getByUsername(@RequestBody User user) {
        UserDetailDto userDetailDto = userLogic.findByUserName(user.getUserCode());
        return ResponseEntity.status(HttpStatus.OK).body(userDetailDto);
    }
}
