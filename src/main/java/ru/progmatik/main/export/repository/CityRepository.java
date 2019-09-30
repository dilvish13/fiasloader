package ru.progmatik.main.export.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.progmatik.main.export.entity.City;

import java.util.stream.Stream;

/**
 * Репозиторий для выгрузки справочника городов в формате CSV для Oracle Commerce.
 *
 * @author M. Golovatiy {@literal <mgolovatiy@at-consulting.ru>}
 * @see <a href="https://devcolibri.com/spring-data-jpa-%D0%BF%D0%B8%D1%88%D0%B5%D0%BC-dao-%D0%B8-services-%D1%87%D0%B0%D1%81%D1%82%D1%8C-2/">Doc  1: services</a>
 * @see <a href="https://docs.spring.io/spring-data/data-commons/docs/current/reference/html/#repositories"> Doc 2: query methods</a>
 */
@Repository
public interface CityRepository extends CrudRepository<City, Long> {

    String exportCitiesQuery = "select" +
            " c.ID, c.NAME, c.AREAS, null as SLUG, null as SUZID, c.FIASCODE, c.CITYTYPE, c.SEARCHABLENAME, c.FULLNAME" +
            " from FIAS_ATG_CITIES c";
    String exportCitiesByAreaQuery = "select" +
            " c.ID, c.NAME, c.AREAS, null as SLUG, null as SUZID, c.FIASCODE, c.CITYTYPE, c.SEARCHABLENAME, c.FULLNAME" +
            " from FIAS_ATG_CITIES c" +
            " where c.AREAS=:area";

    //@QueryHints(value = @QueryHint(name = org.hibernate.jpa.QueryHints.HINT_FETCH_SIZE, value = "" + Integer.MIN_VALUE))
    @Query(value = exportCitiesQuery, nativeQuery = true)
    public Slice<City> streamAll(Pageable pageRequest);

    @Query(value = exportCitiesByAreaQuery, nativeQuery = true)
    public Stream<City> streamByArea(@Param("area") String area);
}
