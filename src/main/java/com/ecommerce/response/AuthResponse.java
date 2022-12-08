package com.ecommerce.response;

import com.ecommerce.models.User;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthResponse {
    private String accessToken;
    private User user;

    public AuthResponse(User user, String accessToken) {
        this.accessToken = accessToken;
        this.user = user;
    }
}
