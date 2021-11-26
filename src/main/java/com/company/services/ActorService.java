package com.company.services;

import com.company.models.Actor;
import com.company.models.Scenario;
import com.company.models.comparators.ActorComparator;
import com.company.models.paging.*;
import com.company.repositories.ActorRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class ActorService {
    private static final Comparator<Actor> EMPTY_COMPARATOR = (e1, e2) -> 0;

    @Autowired
    private ActorRepository repository;

    public List<Actor> findAll() {
        return repository.findAll();
    }

    public void delete(Integer id) {
        repository.deleteById(id);
    }

    public void update(Integer id,
                       String name,
                       Integer age,
                       String number,
                       String role,
                       Scenario scenario,
                       Boolean denyMovie,
                       Boolean denyScenario) {
        Actor actor = repository.findById(id).get();
        actor.setFullName(name);
        actor.setAge(age);
        actor.setTelNumber(number);
        actor.setRole(role);
        actor.setScenario(scenario);
        actor.setDenyMovieShooting(denyMovie);
        actor.setDenyScenario(denyScenario);
        repository.save(actor);
    }

    public void add(String name,
                    Integer age,
                    String number,
                    String role,
                    Scenario scenario,
                    Boolean denyMovie,
                    Boolean denyScenario){
        Actor actor = new Actor(name, number, role, age, denyScenario, denyMovie, scenario);
        repository.save(actor);
    }

    public PageArray getActorsArray(PagingRequest pagingRequest) {
        pagingRequest.setColumns(Stream.of("id", "name", "age", "number", "role", "scenario_id", "denyMovie", "denyScenario")
                .map(Column::new)
                .collect(Collectors.toList()));
        Page<Actor> actorPage = getActors(pagingRequest);

        PageArray pageArray = new PageArray();
        pageArray.setRecordsFiltered(actorPage.getRecordsFiltered());
        pageArray.setRecordsTotal(actorPage.getRecordsTotal());
        pageArray.setDraw(actorPage.getDraw());
        pageArray.setData(actorPage.getData()
                .stream()
                .map(this::toStringList)
                .collect(Collectors.toList()));
        return pageArray;
    }

    private List<String> toStringList(Actor actor) {
        return Arrays.asList(
                actor.getId().toString(),
                actor.getFullName(),
                actor.getAge().toString(),
                actor.getTelNumber(),
                actor.getRole(),
                actor.getScenario().getId().toString(),
                actor.getDenyMovieShooting().toString(),
                actor.getDenyScenario().toString()
        );
    }

    public Page<Actor> getActors(PagingRequest pagingRequest) {
        List<Actor> actors = findAll();
        return getPage(actors, pagingRequest);
    }

    private Page<Actor> getPage(List<Actor> actors, PagingRequest pagingRequest) {
        List<Actor> filtered = actors.stream()
                .sorted(sortActors(pagingRequest))
                .filter(filterActors(pagingRequest))
                .skip(pagingRequest.getStart())
                .limit(pagingRequest.getLength())
                .collect(Collectors.toList());

        long count = actors.stream()
                .filter(filterActors(pagingRequest))
                .count();

        Page<Actor> page = new Page<>(filtered);
        page.setRecordsFiltered((int) count);
        page.setRecordsTotal((int) count);
        page.setDraw(pagingRequest.getDraw());

        return page;
    }

    private Predicate<Actor> filterActors(PagingRequest pagingRequest) {
        if (pagingRequest.getSearch() == null || StringUtils.isEmpty(pagingRequest.getSearch()
                .getValue())) {
            return actor -> true;
        }

        String value = pagingRequest.getSearch()
                .getValue();

        return actor -> actor.getId().toString()
                .contains(value)
                || actor.getScenario().getId().toString()
                .toLowerCase()
                .contains(value)
                || actor.getDenyScenario().toString()
                .toLowerCase()
                .contains(value)
                || actor.getFullName()
                .toLowerCase()
                .contains(value)
                || actor.getRole()
                .toLowerCase()
                .contains(value)
                || actor.getAge().toString()
                .toLowerCase()
                .contains(value)
                || actor.getDenyMovieShooting().toString()
                .toLowerCase()
                .contains(value)
                || actor.getTelNumber()
                .toLowerCase()
                .contains(value);
    }

    private Comparator<Actor> sortActors(PagingRequest pagingRequest) {
        if (pagingRequest.getOrder() == null) {
            return EMPTY_COMPARATOR;
        }

        try {
            Order order = pagingRequest.getOrder()
                    .get(0);

            int columnIndex = order.getColumn();
            Column column = pagingRequest.getColumns()
                    .get(columnIndex);

            Comparator<Actor> comparator = ActorComparator.getComparator(column.getData(), order.getDir());
            if (comparator == null) {
                return EMPTY_COMPARATOR;
            }

            return comparator;

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return EMPTY_COMPARATOR;
    }
}
