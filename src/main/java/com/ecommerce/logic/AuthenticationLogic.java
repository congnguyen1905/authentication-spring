package com.ecommerce.logic;

import com.ecommerce.config.jwt.JwtTokenUtil;
import com.ecommerce.models.User;
import com.ecommerce.response.AuthResponse;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationLogic {
    private final JwtTokenUtil jwtTokenUtil;

    public AuthenticationLogic(JwtTokenUtil jwtTokenUtil) {
        this.jwtTokenUtil = jwtTokenUtil;
    }

    public AuthResponse login(User user) {
        User user1 = new User();
        user1.setFullName("COng");
        String apiKey = jwtTokenUtil.generateToken(user1, user1.getFullName());
        return new AuthResponse(user1, apiKey);
    }
}
