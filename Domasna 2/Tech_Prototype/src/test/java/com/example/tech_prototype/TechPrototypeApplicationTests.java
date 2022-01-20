package com.example.tech_prototype;

import com.example.tech_prototype.Model.Role;
import com.example.tech_prototype.Model.Route;
import com.example.tech_prototype.Model.User;
import com.example.tech_prototype.Service.RouteService;
import com.example.tech_prototype.Service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;


@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@RunWith(SpringRunner.class)
class TechPrototypeApplicationTests {

    MockMvc mockMvc;

    @Autowired
    UserService userService;

    @Autowired
    RouteService routeService;

    private static User u1;
    private static Route r1;
    private static boolean dataInitialized = false;

    @BeforeEach
    public void setup(WebApplicationContext wac){
        this.mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
        initData();
    }

    private void initData() {
        if (!dataInitialized) {
            String user = "user";
            userService.register(user, user, user, user, Role.ROLE_ADMIN);
            dataInitialized = true;
        }
    }

    @Test
    void contextLoads() {
    }

    @Test
    public void testUserGetUser() {
    }
}
