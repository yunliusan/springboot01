package lhonl;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import lhonl.dto.LoginUserDto;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Project02ApplicationTests {
	
	@Autowired
	private RedisTemplate<String,Object> redisTemplate;
	 
	@Autowired
	private WebApplicationContext woc;
	private MockMvc mock;
	
	@Before
	public void setup() {
		mock = MockMvcBuilders.webAppContextSetup(woc).build();
	}
	
	@Test
	public void findUserById() throws Exception {
		String result = mock.perform(MockMvcRequestBuilders.get("/user/2").contentType(MediaType.APPLICATION_JSON_UTF8))
//				.andExpect(MockMvcResultMatchers.status().isOk())
//				.andExpect(MockMvcResultMatchers.jsonPath("$.stateCode").value(300))
				.andReturn().getResponse().getContentAsString();
		
		System.out.println(result);
	}
	
	@Test
	public void findUserByUsername() throws Exception {
		String result = mock.perform(MockMvcRequestBuilders.get("/login").contentType(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andReturn().getResponse().getContentAsString();
		
		System.out.println(result);
	}
	
	@Test
	public void addUser() throws Exception {
//		String content = "{\"username\":\"linhonglin\", \"password\": \"lin123\", \"gender\": \"male\", \"email\": \"137261719@qq.com\", \"remark\": \"none\",\"roleName\":[\"ROLE_USER\"]}";
		String content = "{\"username\":\"\", \"password\": \"lin123\", \"gender\": \"male\", \"email\": \"137261719@qq.com\", \"remark\": \"none\",\"roleName\":[\"ROLE_USER\"]}";
		String result = mock.perform(MockMvcRequestBuilders.post("/user")
					.contentType(MediaType.APPLICATION_JSON_UTF8)
					.content(content))
				.andReturn().getResponse().getContentAsString();
		
		System.out.println(result);
	}
	
	@Test
	public void MyAnnotationTest() throws Exception {
		String content = "{\"username\":\"linhonglin\", \"password\": \"lin123\", \"gender\": \"male\", \"email\": \"137261719@qq.com\", \"remark\": \"none\"}";
		String result = mock.perform(MockMvcRequestBuilders.post("/user")
					.contentType(MediaType.APPLICATION_JSON_UTF8)
					.content(content))
				.andReturn().getResponse().getContentAsString();
		
		System.out.println(result);
	}
	
	@Test
	public void removceUserById() throws Exception {
		String result = mock.perform(MockMvcRequestBuilders.delete("/user/1").contentType(MediaType.APPLICATION_JSON_UTF8))
				.andReturn().getResponse().getContentAsString();		
		System.out.println(result);
	}
	
	@Test
	public void updateUser() throws Exception {
		String content = "{\"username\":\"linhonglin\", \"password\": \"lin123\", \"gender\": \"male\", \"email\": \"137261719@qq.com\", \"remark\": \"none\"}";
		String result = mock.perform(MockMvcRequestBuilders.put("/user/2")
					.contentType(MediaType.APPLICATION_JSON_UTF8)
					.content(content))
				.andReturn().getResponse().getContentAsString();
		
		System.out.println(result);
	}
	
	@Test
	public void page() throws Exception {
		String pageContent = "{\"page\" : 0, \"size\" : 2}";
		String result = mock.perform(MockMvcRequestBuilders.get("/user").contentType(MediaType.APPLICATION_JSON_UTF8).content(pageContent))				
				.andReturn().getResponse().getContentAsString();
		
		System.out.println(result);
	}
	
	@Test
	public void loginTest() throws Exception {
		String result = mock.perform(MockMvcRequestBuilders.get("/login").contentType(MediaType.APPLICATION_JSON_UTF8))
							.andReturn().getResponse().getContentAsString();
		System.out.println(result);
	}
	
	@Test
	public void login() throws Exception {
		
		String content = "{\"username\":\"lin\", \"password\":\"lin123\"}";
		mock.perform(MockMvcRequestBuilders.post("/login").contentType(MediaType.APPLICATION_JSON_UTF8).content(content));
		
		String result01 = mock.perform(MockMvcRequestBuilders.get("/user/2").contentType(MediaType.APPLICATION_JSON_UTF8))
//				.andExpect(MockMvcResultMatchers.status().isOk())
//				.andExpect(MockMvcResultMatchers.jsonPath("$.stateCode").value(300))
				.andReturn().getResponse().getContentAsString();
		System.out.println(result01);
	}
	


	@Test
	public void stringTest(){
	        ValueOperations<String,Object> valueOperations = redisTemplate.opsForValue();
	        valueOperations.set("hello", "redis");
	        ValueOperations<String,Object> valueOperations01 = redisTemplate.opsForValue();
	        LoginUserDto user= LoginUserDto.builder().username("lin").password("lin123").build();
	        valueOperations.set("user", user);
	        System.out.println("useRedisDao = " + valueOperations01.get("user"));
	    }
}
