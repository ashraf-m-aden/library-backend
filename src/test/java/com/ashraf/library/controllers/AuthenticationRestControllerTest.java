package com.ashraf.library.controllers;
import com.ashraf.library.security.*;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.jboss.logging.Logger;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.ashraf.library.TestString;
import com.ashraf.library.dao.BookRepository;
import com.ashraf.library.dao.BorrowRepository;
import com.ashraf.library.dao.ClientRepository;
import com.ashraf.library.dao.GenreRepository;
import com.ashraf.library.dao.UserRepository;
import com.ashraf.library.entity.User;
import com.ashraf.library.security.jwt.JwtUtil;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc
class AuthenticationRestControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	@MockBean
	@Autowired
	private JwtUtil jwtUtil;

	@MockBean
	private TestString TS;
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
		User user = new User("ashraf","test123","ADMIN");
		String jwt = TS.jwt();
		log.info(jwt);
		Mockito.when(uRepo.findByUsernameAndPassword(user.getUsername(), user.getPassword())).thenReturn(user);
		Mockito.when(this.jwtUtil.generateToken(user)).thenReturn(jwt);
		mockMvc.perform(post("/authenticate").contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(user))).andExpect(status().is(201)).andDo(print());

	}
}
