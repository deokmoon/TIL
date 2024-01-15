package com.til.spring.test.mockito;

import com.google.gson.Gson;
import com.til.spring.test.mockito.dto.SignUpRequest;
import com.til.spring.test.mockito.dto.UserResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ExtendWith(MockitoExtension.class)
class UserControllerTest {

    @InjectMocks
    private UserController userController;

    @Mock
    UserService userService;

    private MockMvc mockMvc;

    @BeforeEach
    public void init() {
        mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
    }

    @Test
    final void signUpSuccess() throws Exception {
        // given
        SignUpRequest request = signUpRequest();
        UserResponse response = userResponse();

        doReturn(response).when(userService)
                .signUp(any(SignUpRequest.class));

        ResultActions resultActions = mockMvc.perform(
                MockMvcRequestBuilders.post("/users/signUp")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new Gson().toJson(request))
        );

        MvcResult mvcResult = resultActions.andExpect(status().isCreated())
                .andExpect(jsonPath("pw", response.getPw()).exists())
                .andExpect(jsonPath("email", response.getEmail()).exists())
                .andReturn();
    }

    private UserResponse userResponse() {
        return UserResponse.builder()
                .pw("password")
                .email("test@test.com")
                .build();
    }

    private SignUpRequest signUpRequest() {
        return SignUpRequest.builder()
                .pw("password")
                .email("test@test.com")
                .build();
    }

}