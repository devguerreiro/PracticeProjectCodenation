//package br.com.ErrorCenter.controllers;
//
//import br.com.ErrorCenter.dtos.ApplicationDTO;
//import br.com.ErrorCenter.entities.ApplicationEntity;
//import br.com.ErrorCenter.services.impl.ApplicationServiceImpl;
//import org.codehaus.jackson.map.ObjectMapper;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.http.MediaType;
//import org.springframework.test.context.junit4.SpringRunner;
//import org.springframework.test.web.servlet.MockMvc;
//
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.Mockito.mock;
//import static org.mockito.Mockito.when;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//@RunWith(SpringRunner.class)
//@WebMvcTest(ApplicationController.class)
//public class ApplicationControllerTest {
//
//    @Autowired
//    private MockMvc mockMvc;
//
//    @MockBean
//    private ApplicationServiceImpl applicationService;
//
//    ObjectMapper objectMapper = new ObjectMapper();
//
//    @Test
//    public void create() throws Exception {
//        ApplicationEntity applicationEntity = new ApplicationEntity.Builder().withEmail("teste@teste.com").withPassword("teste123").build();
//
//        ApplicationDTO applicationDTO = mock(ApplicationDTO.class);
//        when(applicationDTO.getId()).thenReturn(1L);
//
//        when(applicationService.save(any(ApplicationEntity.class))).thenReturn(applicationDTO);
//
//        mockMvc.perform(post("/api/v1/application")
//                .content(objectMapper.writeValueAsString(applicationEntity))
//                .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isCreated())
//                .andExpect(jsonPath("$.id").value(applicationDTO.getId()));
//    }
//}
