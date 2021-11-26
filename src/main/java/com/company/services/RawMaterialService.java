package com.company.services;

import com.company.models.RawMaterial;
import com.company.models.RawMaterial;
import com.company.models.Scenario;
import com.company.models.TechTask;
import com.company.models.comparators.EditedMaterialComparator;
import com.company.models.comparators.RawMaterialComparator;
import com.company.models.comparators.ScenarioComparator;
import com.company.models.paging.*;
import com.company.repositories.EditedMaterialRepository;
import com.company.repositories.RawMaterialRepository;
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
public class RawMaterialService {
    private static final Comparator<RawMaterial> EMPTY_COMPARATOR = (e1, e2) -> 0;

    @Autowired
    private RawMaterialRepository repository;

    public RawMaterial findById(Integer id) {
        return repository.findById(id).get();
    }

    public List<RawMaterial> findAll() {
        return repository.findAll();
    }

    public void delete(Integer id) {
        repository.deleteById(id);
    }

    public void update(Integer id,
                       String name,
                       Integer duration,
                       String status,
                       Scenario scenario,
                       TechTask techTask) {
        RawMaterial rawMaterial = repository.findById(id).get();
        rawMaterial.setName(name);
        rawMaterial.setDuration(duration);
        rawMaterial.setStatus(status);
        rawMaterial.setScenario(scenario);
        rawMaterial.setTask(techTask);
        repository.save(rawMaterial);
    }

    public void add(String name,
                    Integer duration,
                    String status,
                    Scenario scenario,
                    TechTask techTask) {
        RawMaterial rawMaterial = new RawMaterial(name, duration, status, scenario, techTask);
        repository.save(rawMaterial);
    }

    public PageArray getRawMaterialArray(PagingRequest pagingRequest) {
        pagingRequest.setColumns(Stream.of("id", "name", "duration", "status", "scenario_id", "techtask_id")
                .map(Column::new)
                .collect(Collectors.toList()));
        Page<RawMaterial> rawMaterialPage = getRawMaterials(pagingRequest);

        PageArray pageArray = new PageArray();
        pageArray.setRecordsFiltered(rawMaterialPage.getRecordsFiltered());
        pageArray.setRecordsTotal(rawMaterialPage.getRecordsTotal());
        pageArray.setDraw(rawMaterialPage.getDraw());
        pageArray.setData(rawMaterialPage.getData()
                .stream()
                .map(this::toStringList)
                .collect(Collectors.toList()));
        return pageArray;
    }

    private List<String> toStringList(RawMaterial rawMaterial) {
        return Arrays.asList(
                rawMaterial.getId().toString(),
                rawMaterial.getName(),
                rawMaterial.getDuration().toString(),
                rawMaterial.getStatus(),
                rawMaterial.getScenarioId().toString(),
                rawMaterial.getTechTaskId().toString()
        );
    }

    public Page<RawMaterial> getRawMaterials(PagingRequest pagingRequest) {
        List<RawMaterial> rawMaterials = findAll();
        return getPage(rawMaterials, pagingRequest);
    }

    private Page<RawMaterial> getPage(List<RawMaterial> rawMaterials, PagingRequest pagingRequest) {
        List<RawMaterial> filtered = rawMaterials.stream()
                .sorted(sortRawMaterial(pagingRequest))
                .filter(filterRawMaterial(pagingRequest))
                .skip(pagingRequest.getStart())
                .limit(pagingRequest.getLength())
                .collect(Collectors.toList());

        long count = rawMaterials.stream()
                .filter(filterRawMaterial(pagingRequest))
                .count();

        Page<RawMaterial> page = new Page<>(filtered);
        page.setRecordsFiltered((int) count);
        page.setRecordsTotal((int) count);
        page.setDraw(pagingRequest.getDraw());

        return page;
    }

    private Predicate<RawMaterial> filterRawMaterial(PagingRequest pagingRequest) {
        if (pagingRequest.getSearch() == null || StringUtils.isEmpty(pagingRequest.getSearch()
                .getValue())) {
            return rawMaterial -> true;
        }

        String value = pagingRequest.getSearch()
                .getValue();

        return rawMaterial -> rawMaterial.getId().toString()
                .contains(value)
                || rawMaterial.getScenarioId().toString()
                .toLowerCase()
                .contains(value)
                || rawMaterial.getTechTaskId().toString()
                .toLowerCase()
                .contains(value)
                || rawMaterial.getDuration().toString()
                .contains(value)
                || rawMaterial.getStatus()
                .toLowerCase()
                .contains(value)
                | rawMaterial.getName()
                .toLowerCase()
                .contains(value);
    }

    private Comparator<RawMaterial> sortRawMaterial(PagingRequest pagingRequest) {
        if (pagingRequest.getOrder() == null) {
            return EMPTY_COMPARATOR;
        }

        try {
            Order order = pagingRequest.getOrder()
                    .get(0);

            int columnIndex = order.getColumn();
            Column column = pagingRequest.getColumns()
                    .get(columnIndex);

            Comparator<RawMaterial> comparator = RawMaterialComparator.getComparator(column.getData(), order.getDir());
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
