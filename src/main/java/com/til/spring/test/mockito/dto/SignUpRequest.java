package com.til.spring.test.mockito.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SignUpRequest {
    private String email;
    private String pw;
}
