package br.com.xyinc.servicegps.service;

import br.com.xyinc.servicegps.SystemGpsApplication;
import br.com.xyinc.servicegps.entities.Poi;
import br.com.xyinc.servicegps.repository.PoiRepository;
import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseOperation;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.DatabaseTearDown;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@TestExecutionListeners({DependencyInjectionTestExecutionListener.class, DirtiesContextTestExecutionListener.class,
        TransactionalTestExecutionListener.class, DbUnitTestExecutionListener.class})
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = SystemGpsApplication.class)
@DatabaseSetup(PoiServiceTest.DATASET)
@DatabaseTearDown(type = DatabaseOperation.DELETE_ALL, value = { PoiServiceTest.DATASET })
@DirtiesContext

public class PoiServiceTest {

    protected static final String DATASET = "classpath:datasets/poi.xml";

    @Autowired
    private PoiRepository repository;

    @Autowired
    private PoiService service;

    @Test
    public void save(){

        Poi poi1 = new Poi();
        poi1.setName("Mecanica");
        poi1.setX(1200L);
        poi1.setY(1400L);

        service.save(poi1);
        assertEquals(HttpStatus.OK, service.save(poi1).getStatusCode());
    }

    @Test
    public void findAll(){
        assertEquals(HttpStatus.OK,service.findAll().getStatusCode());
    }

    @Test
    public void findAllProximity(){
        assertEquals(HttpStatus.OK,service.findAllProximity("20","10","5").getStatusCode());
    }

}
