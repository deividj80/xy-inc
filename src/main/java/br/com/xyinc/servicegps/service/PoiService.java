package br.com.xyinc.servicegps.service;

import br.com.xyinc.servicegps.entities.Poi;
import org.springframework.http.ResponseEntity;

/**
 * Created by deividreis on 07/11/16.
 */
public interface PoiService {

   ResponseEntity<?> save(Poi poi);

   ResponseEntity<?> findAll();

   ResponseEntity<?> findAllProximity(String x, String y , String prox);

}
