package com.amtak.muselspringapi.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

public class MvcMocks {
    private final MockMvc mockMvc;

    private final ObjectMapper objectMapper;

    public MvcMocks(MockMvc mockMvc, ObjectMapper objectMapper) {
        this.mockMvc = mockMvc;
        this.objectMapper = objectMapper;
    }

    public String request(HttpMethod httpMethod, String url, String contentString, HttpStatus httpStatus) throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders.request(httpMethod, url)
                .contentType(MediaType.APPLICATION_JSON)
                .content(contentString);
        ResultMatcher resultMatcher = MockMvcResultMatchers.status().is(httpStatus.value());

        return mockMvc.perform(requestBuilder)
                .andExpect(resultMatcher)
                .andReturn().getResponse().getContentAsString();
    }

    public String request(HttpMethod httpMethod, String url, Object contentValue, HttpStatus httpStatus) throws Exception {
        return request(httpMethod, url, objectMapper.writeValueAsString(contentValue), httpStatus);
    }

    public String get(String url, HttpStatus httpStatus) throws Exception {
        return request(HttpMethod.GET, url, (Object)null, httpStatus);
    }

    public String get(String url) throws Exception {
        return get(url, HttpStatus.OK);
    }

    public String delete(String url, HttpStatus httpStatus) throws Exception {
        return request(HttpMethod.DELETE, url, (Object)null, httpStatus);
    }

    public String delete(String url) throws Exception {
        return delete(url, HttpStatus.OK);
    }
}
