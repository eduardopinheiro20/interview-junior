package br.com.brainweb.interview.core.features.hero;

import br.com.brainweb.interview.model.Hero;
import br.com.brainweb.interview.model.request.CreateHeroRequest;
import br.com.brainweb.interview.model.request.DeleteHeroRequest;
import br.com.brainweb.interview.model.request.SearchHeroRequest;
import br.com.brainweb.interview.model.request.UpdateHeroRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.List;
import java.util.UUID;

import static java.lang.String.format;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.http.ResponseEntity.created;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/v1/heroes", produces = APPLICATION_JSON_VALUE)
public class HeroController {

    private HeroService heroService;

    @PostMapping(consumes = APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> create(@Validated
            @RequestBody CreateHeroRequest createHeroRequest) {
        final UUID id = heroService.create(createHeroRequest);
        return created(URI.create(format("/api/v1/heroes/create/%s", id))).build();
    }

    public ResponseEntity<Void> search(@Validated
            @RequestBody SearchHeroRequest searchHeroRequest) {
        final List<Hero> heroes = heroService.search(searchHeroRequest);
        return created(URI.create(format("/api/v1/heroes/search/%s", searchHeroRequest))).build();
    }

    @PostMapping(consumes = APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> update(@Validated
            @RequestBody UpdateHeroRequest updateHeroRequest) {
        final UUID id = heroService.update(updateHeroRequest);
        return created(URI.create(format("/api/v1/heroes/update/%s", id))).build();
    }

    public ResponseEntity<Void> delete(@Validated
            @RequestBody DeleteHeroRequest deleteHeroRequest) {
        final Boolean id = heroService.delete(deleteHeroRequest);
        return created(URI.create(format("/api/v1/heroes/delete/%s", id))).build();
    }
}
