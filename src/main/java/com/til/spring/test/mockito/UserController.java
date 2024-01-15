package com.til.spring.test.mockito;

import com.til.spring.test.mockito.dto.SignUpRequest;
import com.til.spring.test.mockito.dto.UserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/users/signUp")
    public ResponseEntity<UserResponse> signUp(@RequestBody SignUpRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(userService.signUp(request));
    }

    @GetMapping("/users")
    public ResponseEntity<List<UserResponse>> findAll() {
        return ResponseEntity.ok(userService.findAll());
    }
}
