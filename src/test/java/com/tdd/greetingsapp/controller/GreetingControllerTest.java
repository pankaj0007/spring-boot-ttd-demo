package com.tdd.greetingsapp.controller;

import com.tdd.greetingsapp.service.GreetingService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import static org.mockito.Mockito.*;

@SpringBootTest
@AutoConfigureMockMvc
public class GreetingControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    GreetingService greetingService;

    @Test
    public void maleGreeting() throws Exception {
        String name = "pankaj";
        String gender = "male";

        when(greetingService.getGreetingByGender(gender)).thenReturn("Mr.");

        ResultActions actions = mockMvc.perform(MockMvcRequestBuilders
                            .get("/web-app/greet")
                            .param("name", name)
                            .param("gender", gender));

        actions.andExpect(MockMvcResultMatchers.status().isOk())
                        .andExpect(MockMvcResultMatchers.content()
                        .string(String.format("Hello Mr. %s. How are you?",name))).
                         andDo(MockMvcResultHandlers.print());
        verify(greetingService).getGreetingByGender(any());

    }

    @Test
    public void femaleGreeting() throws Exception {
        String name = "Jyoti";
        String gender = "female";

        when(greetingService.getGreetingByGender(gender)).thenReturn("Miss.");

        ResultActions actions = mockMvc.perform(MockMvcRequestBuilders
                .get("/web-app/greet")
                .param("name", name)
                .param("gender", gender));

        actions.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content()
                        .string(String.format("Hello Miss. %s. How are you?",name))).
                andDo(MockMvcResultHandlers.print());

        verify(greetingService).getGreetingByGender(any());
    }
}
