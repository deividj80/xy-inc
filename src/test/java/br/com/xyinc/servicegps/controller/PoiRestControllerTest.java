package br.com.xyinc.servicegps.controller;


import br.com.xyinc.servicegps.entities.Poi;
import br.com.xyinc.servicegps.service.PoiService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@WebMvcTest(PoiRestController.class)
public class PoiRestControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private PoiService service;

    @Test
    public void save() throws Exception {

        String request = "{\"name\": \"teste\", \"x\":\"11111\",\"y\":\"22222\"}";

        ResponseEntity r = new ResponseEntity<String>(HttpStatus.OK);
        when(service.save(new Poi())).thenReturn(r);

        mvc.perform(post("/poi/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(request))
                .andExpect(status().isOk());
    }

    @Test
    public void list() throws Exception {

        ResponseEntity r = new ResponseEntity<String>(HttpStatus.OK);
        when(service.findAll()).thenReturn(r);

        mvc.perform(get("/poi/list").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());

    }

    @Test
    public void proximity() throws Exception {

        ResponseEntity r = new ResponseEntity<String>(HttpStatus.OK);
        when(service.findAllProximity("22", "33", "11")).thenReturn(r);

        mvc.perform(get("/poi/proximity?x=22&y=33&prox=11").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());

    }


}
