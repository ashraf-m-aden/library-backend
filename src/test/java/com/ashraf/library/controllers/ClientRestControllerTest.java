package com.ashraf.library.controllers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.jboss.logging.Logger;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.ashraf.library.dao.BookRepository;
import com.ashraf.library.dao.BorrowRepository;
import com.ashraf.library.dao.ClientRepository;
import com.ashraf.library.dao.GenreRepository;
import com.ashraf.library.dao.UserRepository;
import com.ashraf.library.entity.User;
import com.ashraf.library.security.jwt.JwtUtil;
import com.ashraf.library.services.ClientService;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc
public class ClientRestControllerTest {

	// to simulate HTTP requests
	@Autowired
	private MockMvc mockMvc;

	// to map to and from JSON
	@Autowired
	private ObjectMapper objectMapper;
	
	@MockBean
	private ClientService clientService;

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

	@MockBean
	private JwtUtil jwtUtil;

	String UserConnection() throws Exception {
		Logger log = Logger.getLogger(AuthenticationRestController.class);
		User user = new User("ashraf", "test123", "ADMIN");
		MvcResult result = mockMvc
				.perform(post("/authenticateTest").accept(MediaType.APPLICATION_JSON)
						.content(objectMapper.writeValueAsString(user)))
				.andExpect(status().is(201)).andDo(print()).andReturn();

		String content = result.getResponse().getContentAsString();
		JSONObject resultsJObject = new JSONObject(content);
		String token = resultsJObject.get("jwt").toString();
		log.info(token);
		return token;

	}

	@Test
	void getAllTheClients() throws Exception {

			Logger log = Logger.getLogger(AuthenticationRestController.class);
			String token = UserConnection();
			mockMvc.perform(get("/clients").contentType("application/json").header("Authorization", "Bearer "+token)).andExpect(status().isOk()).andDo(print());
	}
	
}
