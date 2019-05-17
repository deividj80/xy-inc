package br.com.xyinc.servicegps.repository;

import br.com.xyinc.servicegps.entities.Poi;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by deividreis on 07/11/16.
 */

@Transactional(rollbackFor=RuntimeException.class)
public interface PoiRepository extends CrudRepository<Poi, Long> {

    //buscar os objetos pelo id
    Poi findById (long id);

    //busca todos objetos
    List<Poi> findAll();

    //busca objetos das proximidades
    List<Poi> findByXGreaterThanEqualAndXLessThanEqualAndYGreaterThanEqualAndYLessThanEqual(Long x1, Long x2 , Long y1 , Long y2 );

    //busca objetos pelo nome
    Poi findByName(String name);
}
