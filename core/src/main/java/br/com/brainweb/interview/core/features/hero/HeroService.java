package br.com.brainweb.interview.core.features.hero;

import br.com.brainweb.interview.core.features.powerstats.PowerStatsService;
import br.com.brainweb.interview.model.Hero;
import br.com.brainweb.interview.model.PowerStats;
import br.com.brainweb.interview.model.request.CreateHeroRequest;
import br.com.brainweb.interview.model.request.DeleteHeroRequest;
import br.com.brainweb.interview.model.request.SearchHeroRequest;
import br.com.brainweb.interview.model.request.UpdateHeroRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class HeroService {

    private HeroRepository heroRepository;
    private PowerStatsService powerStatsService;

    @Transactional
    public UUID create(CreateHeroRequest createHeroRequest) {
        UUID id = powerStatsService.create(new PowerStats(createHeroRequest));
        return heroRepository.create(new Hero(createHeroRequest, id));
    }

    @Transactional
    public List<Hero> search(SearchHeroRequest searchHeroRequest) {
        if (searchHeroRequest.getId() != null) {
            return heroRepository.searchById(searchHeroRequest.getId());
        }
        if (searchHeroRequest.getName() != null) {
            return heroRepository.searchByName(searchHeroRequest.getName());
        }
        return heroRepository.search();
    }

    @Transactional
    public UUID update(UpdateHeroRequest updateHeroRequest) {
        UUID id = powerStatsService.create(new PowerStats(updateHeroRequest));
        return heroRepository.updateById(new Hero(updateHeroRequest, id));
    }

    @Transactional
    public boolean delete(DeleteHeroRequest deleteHeroRequest) {
        return heroRepository.deleteById(deleteHeroRequest.getId());
    }

}
