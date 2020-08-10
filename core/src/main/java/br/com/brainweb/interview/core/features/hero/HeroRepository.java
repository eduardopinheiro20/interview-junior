package br.com.brainweb.interview.core.features.hero;

import br.com.brainweb.interview.model.Hero;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class HeroRepository {

    private static final String CREATE_HERO_QUERY = "INSERT INTO hero" +
        " (name, race, power_stats_id)" +
        " VALUES (:name, :race, :powerStatsId) RETURNING id";

    private static final String SEARCH_HERO_BY_ID_QUERY = "SELECT * FROM hero "+
            "WHERE id = :id";

    private static final String SEARCH_HERO_BY_FILTER_QUERY = "SELECT * FROM hero "+
            "WHERE name = :name";

    private static final String SEARCH_HERO_QUERY = "SELECT * FROM hero ";

    private static final String UPDATE_HERO_BY_ID_QUERY = "UPDATE hero SET " +
            "name = :name," +
            "race = :race" +
            "powerStatsId = :powerStatsId";

    private static final String DELETE_HERO_BY_ID_QUERY = "DELETE FROM hero WHERE id = :id";

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    UUID create(Hero hero) {
        final Map<String, Object> params = Map.of("name", hero.getName(),
            "race", hero.getRace().name(),
            "powerStatsId", hero.getPowerStatsId());
        return namedParameterJdbcTemplate.queryForObject(
            CREATE_HERO_QUERY,
            params,
            UUID.class);
    }

    List<Hero> searchById(UUID id){
        final Map<String, Object> params = Map.of("id", id);
        return namedParameterJdbcTemplate.queryForObject(SEARCH_HERO_BY_ID_QUERY, params, List.class);
    }

    List<Hero> searchByName(String name){
        final Map<String, Object> params = Map.of("name", name);
        return namedParameterJdbcTemplate.queryForObject(SEARCH_HERO_BY_FILTER_QUERY, params, List.class);
    }

    List<Hero> search(){
        return namedParameterJdbcTemplate.queryForObject(SEARCH_HERO_QUERY, (SqlParameterSource) null,List.class);
    }

    UUID updateById(Hero hero) {
        final Map<String, Object> params = Map.of("id",hero.getId());
        return namedParameterJdbcTemplate.queryForObject(UPDATE_HERO_BY_ID_QUERY,params,UUID.class);
    }

    Boolean deleteById(UUID id){
        final Map<String, Object> params = Map.of("id", id);
        return namedParameterJdbcTemplate.queryForObject(DELETE_HERO_BY_ID_QUERY,params,Boolean.class);
    }
}
