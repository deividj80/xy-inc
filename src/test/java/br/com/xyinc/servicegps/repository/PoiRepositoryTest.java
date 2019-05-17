package br.com.xyinc.servicegps.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import br.com.xyinc.servicegps.SystemGpsApplication;
import br.com.xyinc.servicegps.entities.Poi;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.*;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;



import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.*;

@TestExecutionListeners({DependencyInjectionTestExecutionListener.class, DirtiesContextTestExecutionListener.class,
        TransactionalTestExecutionListener.class, DbUnitTestExecutionListener.class})
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = SystemGpsApplication.class)
@DatabaseSetup(PoiRepositoryTest.DATASET)
@DatabaseTearDown(type = DatabaseOperation.DELETE_ALL, value = { PoiRepositoryTest.DATASET })
@DirtiesContext
public class PoiRepositoryTest {

    protected static final String DATASET = "classpath:datasets/poi.xml";

    @Autowired
    private PoiRepository repository;

    @Test
    public void save(){
        Poi poi = new Poi();
        poi.setName("Padaria");
        poi.setX(1200L);
        poi.setY(1400L);
        repository.save(poi);
        assertNotNull(repository.findByName("Padaria"));
    }

    @Test
    public void findById(){
        assertNotNull(repository.findById(1L));
    }

    @Test
    public void findAll(){
        assertEquals(7,repository.findAll().size());
    }

    @Test
    public void findByName(){
        assertNotNull(repository.findByName("Pub"));
    }

    @Test
    public void findByXGreaterThanEqualAndXLessThanEqualAndYGreaterThanEqualAndYLessThanEqual(){
        assertEquals(5,repository.findByXGreaterThanEqualAndXLessThanEqualAndYGreaterThanEqualAndYLessThanEqual(10L,30L,0L,20L).size());
    }

}
