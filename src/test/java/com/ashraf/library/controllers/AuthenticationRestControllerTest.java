package com.ashraf.library.controllers;

import javax.persistence.EntityManager;
import javax.sql.DataSource;

import org.jboss.logging.Logger;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;

import static org.assertj.core.api.Assertions.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import com.ashraf.library.LibraryApplication;
import com.ashraf.library.dao.BookRepository;
import com.ashraf.library.dao.BorrowRepository;
import com.ashraf.library.dao.ClientRepository;
import com.ashraf.library.dao.GenreRepository;
import com.ashraf.library.dao.UserRepository;
import com.ashraf.library.entity.User;
import com.ashraf.library.security.jwt.JwtUtil;
import com.ashraf.library.services.UserService;
import com.ashraf.library.services.UserServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc
class AuthenticationRestControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	@MockBean
	private JwtUtil jwtUtil;

	@MockBean
	private BorrowRepository bRepo;
	@MockBean
	private UserRepository uRepo;
	@MockBean
	private ClientRepository cRepo;
	@MockBean
	private GenreRepository gRepo;
	@MockBean
	private BookRepository bookRepo;


	@Test
	void testUserConnection() throws Exception {
		Logger log = Logger.getLogger(AuthenticationRestController.class);
		mockMvc.perform(post("/authenticateTest")).andExpect(status().is(201)).andDo(print());

	}
}
