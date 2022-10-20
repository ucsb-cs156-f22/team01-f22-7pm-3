package edu.ucsb.cs156.spring.backenddemo.controllers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import edu.ucsb.cs156.spring.backenddemo.services.RedditQueryService;

import static org.mockito.ArgumentMatchers.eq;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@WebMvcTest(value = RedditController.class)
public class RedditControllerTest {
  @Autowired
  private MockMvc mockMvc;
  @MockBean
  RedditQueryService mockRedditQueryService;


  @Test
  public void test_getSubreddit() throws Exception {
  
    String fakeJsonResult="{ \"fake\" : \"result\" }";
    String subreddit = "UCSantaBarbara";
    when(mockRedditQueryService.getJSON(eq(subreddit))).thenReturn(fakeJsonResult);

    String url = String.format("/api/reddit/get?subreddit=%s", subreddit);

    MvcResult response = mockMvc
        .perform( get(url).contentType("application/json"))
        .andExpect(status().isOk()).andReturn();

    String responseString = response.getResponse().getContentAsString();

    assertEquals(fakeJsonResult, responseString);
  }

}