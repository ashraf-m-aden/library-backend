package com.ashraf.library;

import com.ashraf.library.controllers.ClientRestController;
import com.ashraf.library.entity.Client;
import com.ashraf.library.services.ClientService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(SpringExtension.class)
@WebMvcTest(ClientRestController.class)
class ClientRestControllerTest {

    @Autowired
    private MockMvc mvc;

    @Test
    void findAll() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/clients");
        MvcResult result = mvc.perform(requestBuilder).andReturn();
    }

    @Test
    void getClientl() {
    }

    @Test
    void save() {
    }
}