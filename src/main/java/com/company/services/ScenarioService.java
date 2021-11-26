package com.company.services;

import com.company.models.Scenario;
import com.company.models.comparators.ScenarioComparator;
import com.company.models.paging.*;
import com.company.repositories.ScenarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class ScenarioService {
    private static final Comparator<Scenario> EMPTY_COMPARATOR = (e1, e2) -> 0;

    @Autowired
    private ScenarioRepository repository;

    public Scenario findById(Integer id) {
        return repository.findById(id).get();
    }

    public List<Scenario> findAll() {
        return repository.findAll();
    }

    public void delete(Integer id) {
        repository.deleteById(id);
    }

    public void update(Integer id,
                       Integer version,
                       Boolean approval,
                       String link) {
        Scenario scenario = repository.findById(id).get();
        scenario.setVersion(version);
        scenario.setApproval(approval);
        scenario.setLink(link);
        repository.save(scenario);
    }

    public void add(Integer version,
                    Boolean approval,
                    String link) {
        Scenario scenario = new Scenario(approval, version, link);
        repository.save(scenario);
    }

    public PageArray getScenarioArray(PagingRequest pagingRequest) {
        pagingRequest.setColumns(Stream.of("id", "version", "approval", "link")
                .map(Column::new)
                .collect(Collectors.toList()));
        Page<Scenario> scenarioPage = getScenario(pagingRequest);

        PageArray pageArray = new PageArray();
        pageArray.setRecordsFiltered(scenarioPage.getRecordsFiltered());
        pageArray.setRecordsTotal(scenarioPage.getRecordsTotal());
        pageArray.setDraw(scenarioPage.getDraw());
        pageArray.setData(scenarioPage.getData()
                .stream()
                .map(this::toStringList)
                .collect(Collectors.toList()));
        return pageArray;
    }

    private List<String> toStringList(Scenario scenario) {
        return Arrays.asList(
                scenario.getId().toString(),
                scenario.getVersion().toString(),
                scenario.getApproval().toString(),
                scenario.getLink()
        );
    }

    public Page<Scenario> getScenario(PagingRequest pagingRequest) {
        List<Scenario> scenarios = findAll();
        return getPage(scenarios, pagingRequest);
    }

    private Page<Scenario> getPage(List<Scenario> scenarios, PagingRequest pagingRequest) {
        List<Scenario> filtered = scenarios.stream()
                .sorted(sortScenario(pagingRequest))
                .filter(filterScenario(pagingRequest))
                .skip(pagingRequest.getStart())
                .limit(pagingRequest.getLength())
                .collect(Collectors.toList());

        long count = scenarios.stream()
                .filter(filterScenario(pagingRequest))
                .count();

        Page<Scenario> page = new Page<>(filtered);
        page.setRecordsFiltered((int) count);
        page.setRecordsTotal((int) count);
        page.setDraw(pagingRequest.getDraw());

        return page;
    }

    private Predicate<Scenario> filterScenario(PagingRequest pagingRequest) {
        if (pagingRequest.getSearch() == null || StringUtils.isEmpty(pagingRequest.getSearch()
                .getValue())) {
            return scenario -> true;
        }

        String value = pagingRequest.getSearch()
                .getValue();

        return scenario -> scenario.getId().toString()
                .contains(value)
                || scenario.getApproval().toString()
                .toLowerCase()
                .contains(value)
                || scenario.getLink()
                .toLowerCase()
                .contains(value)
                || scenario.getVersion().toString()
                .toLowerCase()
                .contains(value);
    }

    private Comparator<Scenario> sortScenario(PagingRequest pagingRequest) {
        if (pagingRequest.getOrder() == null) {
            return EMPTY_COMPARATOR;
        }

        try {
            Order order = pagingRequest.getOrder()
                    .get(0);

            int columnIndex = order.getColumn();
            Column column = pagingRequest.getColumns()
                    .get(columnIndex);

            Comparator<Scenario> comparator = ScenarioComparator.getComparator(column.getData(), order.getDir());
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
