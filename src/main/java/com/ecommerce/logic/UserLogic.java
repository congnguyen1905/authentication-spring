package com.ecommerce.logic;

import com.ecommerce.dto.user.UserDetailDto;
import org.springframework.stereotype.Service;

@Service
public class UserLogic {
    public UserDetailDto findByUserName(String userName) {
        return new UserDetailDto();
    }
}
