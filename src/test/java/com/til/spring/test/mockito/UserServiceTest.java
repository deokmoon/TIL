package com.til.spring.test.mockito;

import com.til.spring.test.mockito.dto.SignUpRequest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @InjectMocks
    UserService userService;
    @Mock
    UserUtils userUtils;
    @Mock
    LocalUtils localUtils;

    @Test
    final void mockTest()  {
//        LocalUtils mock = Mockito.mock(LocalUtils.class);
        doReturn(0).when(userUtils)
                .userAgeMinus(any(Integer.class));
        doReturn(0)
                .when(localUtils).plus(any(Integer.class)); // local Scope -> 2 return

        assertThat(userService.ageValue()).isEqualTo(2);

    }

}