package br.com.xyinc.servicegps.service.Impl;

import br.com.xyinc.servicegps.entities.Poi;
import br.com.xyinc.servicegps.repository.PoiRepository;
import br.com.xyinc.servicegps.service.PoiService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by deividreis on 07/11/16.
 */

@Service
public class PoiServiceImpl implements PoiService{

    private final Logger log = LoggerFactory.getLogger(this.getClass());


    @Autowired
    private PoiRepository repository;

    @Override
    public ResponseEntity<String> save(Poi poi) {

        log.info("Inciando a criação do objeto POI.[name:{} - x:{} - y:{}]",poi.getName(),poi.getX(),poi.getY());

        String data="";

        try {

            Map<String, Object> json = new HashMap<String, Object>();
            json.put("status", 200);
            json.put("message", "Poi criado com sucesso;" );

            ObjectMapper mapper = new ObjectMapper();
            data  = mapper.writeValueAsString(json);

        } catch (Exception e) {
            log.error("Erro em criar o objeto POI.[name:{} - x:{} - y:{}] Erro:{}",poi.getName(),poi.getX(),poi.getY(),e);

            e.printStackTrace();
        }

        repository.save(new Poi( poi.getName(),poi.getX(),poi.getY()));

        return new ResponseEntity<String>(data, HttpStatus.OK);

    }

    @Override
    public ResponseEntity<String> findAll() {


        log.info("Inciando a busca dos objetos POI.");

        String data="";

        try {

            List<Poi> poiList = repository.findAll();

            Map<String, Object> json = new HashMap<String, Object>();
            json.put("status", 200);

            if (poiList == null || poiList.isEmpty()){
                json.put("message", "Não foi encontrado nenhum objeto Poi." );
            }else{
                json.put("message", "Busca feita com sucesso." );
            }

            json.put("poi", poiList);

            ObjectMapper mapper = new ObjectMapper();
            data  = mapper.writeValueAsString(json);

        } catch (Exception e) {
            log.error("Erro em busca os objetos POI. Erro: {}",e);
        }

        log.info("Finalizado a busca dos objetos POI.Response:{}",data.toString());

        return new ResponseEntity<String>(data, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<String> findAllProximity(String x, String y, String prox) {


        log.info("Inciando a busca das proximidades.[x:{} - y:{} - proximity:{}]",x,y,prox);

        String data="";

        Long lx = Long.parseLong(x);
        Long ly = Long.parseLong(y);
        Long lprox = Long.parseLong(prox);

        try {

            List<Poi> poiList = repository.findByXGreaterThanEqualAndXLessThanEqualAndYGreaterThanEqualAndYLessThanEqual(lx-lprox,lx+lprox,ly-lprox,ly+lprox);

            Map<String, Object> json = new HashMap<String, Object>();
            json.put("status", 200);

            if (poiList == null || poiList.isEmpty()){
                json.put("message", "Não foi encontrado objetos Poi nas proximidades." );
            }else{
                json.put("message", "Busca feita com sucesso." );
            }

            json.put("poi", poiList);

            ObjectMapper mapper = new ObjectMapper();
            data  = mapper.writeValueAsString(json);

        } catch (Exception e) {
            log.error("Erro em buscar as proximidades.[x:{} - y:{} - proximity:{}] Erro:{}",x,y,prox,e);
        }

        log.info("Finalizado a busca das proximidades.[x:{} - y:{} - proximity:{}] Response:{}",x,y,prox,data.toString());

        return new ResponseEntity<String>(data,HttpStatus.OK);

    }


}
