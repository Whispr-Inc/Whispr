package com.whispr.messenger;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@WithMockUser(username = "tester")
public class ChatControllerTests {

    @Autowired
    MockMvc mockMvc;

    @Test
    public void contextLoads() {

    }

    @Test
    public void testDummyEndpoint() throws Exception {
        mockMvc.perform(get("/api/chat/dummy"))
                .andExpect(status().isOk());
    }
}
