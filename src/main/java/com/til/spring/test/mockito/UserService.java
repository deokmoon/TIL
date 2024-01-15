package com.til.spring.test.mockito;

import com.til.spring.test.mockito.dto.SignUpRequest;
import com.til.spring.test.mockito.dto.UserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserUtils userUtils;

    public UserResponse signUp(SignUpRequest request) {
        return null;
    }

    public List<UserResponse> findAll() {
        return null;
    }

    public int ageValue() {
        int beanValue = userUtils.userAgeMinus(5);
        LocalUtils localUtils = new LocalUtils();
        int localValue = localUtils.plus(1);

        System.out.println("beanValue: " + beanValue);
        System.out.println("localValue: " + localValue);
        return beanValue + localValue;
    }
}
