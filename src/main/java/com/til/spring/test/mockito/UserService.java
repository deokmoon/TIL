package com.til.spring.test.mockito;

import com.til.spring.test.mockito.dto.SignUpRequest;
import com.til.spring.test.mockito.dto.UserResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    public UserResponse signUp(SignUpRequest request) {
        return null;
    }

    public List<UserResponse> findAll() {
        return null;
    }
}
