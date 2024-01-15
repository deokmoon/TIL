package com.til.spring.test.mockito;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@NoArgsConstructor
public class UserUtils {

    public int userAgeMinus(int age) {
        return age - 1;
    }

    public static int userAgePlus(int age) {
        return age + 1;
    }
}
