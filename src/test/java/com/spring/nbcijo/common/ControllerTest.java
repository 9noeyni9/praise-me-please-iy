package com.spring.nbcijo.common;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.spring.nbcijo.config.WebSecurityConfig;
import com.spring.nbcijo.controller.CommentController;
import com.spring.nbcijo.controller.MyPageController;
import com.spring.nbcijo.security.UserDetailsImpl;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.data.jpa.mapping.JpaMetamodelMappingContext;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@WebMvcTest(
    controllers = {CommentController.class, MyPageController.class},
    excludeFilters = {
        @ComponentScan.Filter(
            type = FilterType.ASSIGNABLE_TYPE,
            classes = WebSecurityConfig.class
        )
    }
)
@MockBean(JpaMetamodelMappingContext.class)
public class ControllerTest implements UserFixture{

    @Autowired
    protected MockMvc mockMvc;

    @Autowired
    protected ObjectMapper objectMapper;

    @Autowired
    protected WebApplicationContext context;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders
            .webAppContextSetup(context)
            .build();

        UserDetailsImpl testUserDetails = new UserDetailsImpl(TEST_USER);

        SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(
            testUserDetails, testUserDetails.getPassword(), testUserDetails.getAuthorities()));
    }
}
