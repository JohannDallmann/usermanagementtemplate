package de.jd.usermanagementtemplate.helloworld.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class HelloWorldControllerEndToEndTest {

    @Autowired
    private MockMvc mockMvc;


    @Test
    @WithMockUser(roles = "client-user")
    void sayHelloUser_returnIsOk_user() throws Exception {
        mockMvc.perform(get("/helloworld/user")
                        .with(user("testUser").roles("client-user")))
                .andExpect(status().isOk())
                .andExpect(content().string("Hello User!"));
    }

    @Test
    void sayHelloUser_returnIsUnauthorized_noRole() throws Exception {
        mockMvc.perform(get("/helloworld/user"))
                .andExpect(status().isUnauthorized());
    }

    @Test
    @WithMockUser(roles = "client-admin")
    void sayHelloAdmin() throws Exception {
        mockMvc.perform(get("/helloworld/admin")
                        .with(user("testUser").roles("client-admin")))
                .andExpect(status().isOk())
                .andExpect(content().string("Hello Admin!"));
    }

    @Test
    @WithMockUser(roles = "client-user")
    void sayHelloAdmin_returnIsForbidden_user() throws Exception {
        mockMvc.perform(get("/helloworld/admin")
                        .with(user("testUser").roles("client-user")))
                .andExpect(status().isForbidden());;
    }
}