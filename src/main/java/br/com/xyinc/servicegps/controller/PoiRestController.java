package br.com.xyinc.servicegps.controller;

import br.com.xyinc.servicegps.entities.Poi;
import br.com.xyinc.servicegps.service.PoiService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by deividreis on 07/11/16.
 */

@RestController
@RequestMapping("/poi")
public class PoiRestController {

    private final Logger log = LoggerFactory.getLogger(this.getClass());


    @Autowired
    private PoiService poiService;

    @ResponseBody
    @RequestMapping(value = "/create", method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<?> create (@RequestBody Poi poi)
    {
        return poiService.save(poi);
    }

    @ResponseBody
    @RequestMapping(value = "/list", method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<?> list ()
    {
        return poiService.findAll();
    }

    @ResponseBody
    @RequestMapping(value = "/proximity", method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<?> proximity (String x, String y, String prox)
    {

        return poiService.findAllProximity(x,y,prox);

    }


}
