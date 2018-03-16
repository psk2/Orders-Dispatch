package com.yathiTest;
import com.yathi.Controller.OrdersController;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringJUnit4ClassRunner.class)
//@WebMvcTest
//@SpringBootTest
public class OrdersControllerTest {

        private MockMvc mockMvc;

        @InjectMocks
        private OrdersController ordersController;

        @Before
        public void setup() throws Exception{
            mockMvc = MockMvcBuilders.standaloneSetup(ordersController)
                    .build();
        }

        @Test
        public void testcreateOrder() throws Exception{
            String json = "{\n" +
                    "\"bricks\":\"20\""+
                    ""+
                    "}";

            mockMvc.perform(
                    MockMvcRequestBuilders.get("/orders/create")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(json)
            )

//                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.bricks", Matchers.is("20")));
        }

}
