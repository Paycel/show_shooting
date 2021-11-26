package com.company.services;

import com.company.models.EditedMaterial;
import com.company.models.RawMaterial;
import com.company.models.comparators.EditedMaterialComparator;
import com.company.models.comparators.ScenarioComparator;
import com.company.models.paging.*;
import com.company.repositories.EditedMaterialRepository;
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
public class EditedMaterialService {
    private static final Comparator<EditedMaterial> EMPTY_COMPARATOR = (e1, e2) -> 0;

    @Autowired
    private EditedMaterialRepository repository;

    public EditedMaterial findById(Integer id) {
        return repository.findById(id).get();
    }

    public List<EditedMaterial> findAll() {
        return repository.findAll();
    }

    public void delete(Integer id) {
        repository.deleteById(id);
    }

    public void update(Integer id,
                       String name,
                       Integer duration,
                       String status,
                       RawMaterial rawMaterial) {
        EditedMaterial editedMaterial = repository.findById(id).get();
        editedMaterial.setName(name);
        editedMaterial.setDuration(duration);
        editedMaterial.setStatus(status);
        editedMaterial.setRaw(rawMaterial);
        repository.save(editedMaterial);
    }

    public void add(String name,
                    Integer duration,
                    String status,
                    RawMaterial rawMaterial) {
        EditedMaterial editedMaterial = new EditedMaterial(name, duration, status, rawMaterial);
        repository.save(editedMaterial);
    }

    public PageArray getEditedMaterialArray(PagingRequest pagingRequest) {
        pagingRequest.setColumns(Stream.of("id", "name", "duration", "status", "raw_id")
                .map(Column::new)
                .collect(Collectors.toList()));
        Page<EditedMaterial> editedMaterialPage = getEditedMaterials(pagingRequest);

        PageArray pageArray = new PageArray();
        pageArray.setRecordsFiltered(editedMaterialPage.getRecordsFiltered());
        pageArray.setRecordsTotal(editedMaterialPage.getRecordsTotal());
        pageArray.setDraw(editedMaterialPage.getDraw());
        pageArray.setData(editedMaterialPage.getData()
                .stream()
                .map(this::toStringList)
                .collect(Collectors.toList()));
        return pageArray;
    }

    private List<String> toStringList(EditedMaterial editedMaterial) {
        return Arrays.asList(
                editedMaterial.getId().toString(),
                editedMaterial.getName(),
                editedMaterial.getDuration().toString(),
                editedMaterial.getStatus(),
                editedMaterial.getRawMaterialId().toString()
        );
    }

    public Page<EditedMaterial> getEditedMaterials(PagingRequest pagingRequest) {
        List<EditedMaterial> editedMaterials = findAll();
        return getPage(editedMaterials, pagingRequest);
    }

    private Page<EditedMaterial> getPage(List<EditedMaterial> editedMaterials, PagingRequest pagingRequest) {
        List<EditedMaterial> filtered = editedMaterials.stream()
                .sorted(sortEditedMaterial(pagingRequest))
                .filter(filterEditedMaterial(pagingRequest))
                .skip(pagingRequest.getStart())
                .limit(pagingRequest.getLength())
                .collect(Collectors.toList());

        long count = editedMaterials.stream()
                .filter(filterEditedMaterial(pagingRequest))
                .count();

        Page<EditedMaterial> page = new Page<>(filtered);
        page.setRecordsFiltered((int) count);
        page.setRecordsTotal((int) count);
        page.setDraw(pagingRequest.getDraw());

        return page;
    }

    private Predicate<EditedMaterial> filterEditedMaterial(PagingRequest pagingRequest) {
        if (pagingRequest.getSearch() == null || StringUtils.isEmpty(pagingRequest.getSearch()
                .getValue())) {
            return editedMaterial -> true;
        }

        String value = pagingRequest.getSearch()
                .getValue();

        return editedMaterial -> editedMaterial.getId().toString()
                .contains(value)
                || editedMaterial.getRawMaterialId().toString()
                .toLowerCase()
                .contains(value)
                || editedMaterial.getDuration().toString()
                .contains(value)
                || editedMaterial.getStatus()
                .toLowerCase()
                .contains(value)
                | editedMaterial.getName()
                .toLowerCase()
                .contains(value);
    }

    private Comparator<EditedMaterial> sortEditedMaterial(PagingRequest pagingRequest) {
        if (pagingRequest.getOrder() == null) {
            return EMPTY_COMPARATOR;
        }

        try {
            Order order = pagingRequest.getOrder()
                    .get(0);

            int columnIndex = order.getColumn();
            Column column = pagingRequest.getColumns()
                    .get(columnIndex);

            Comparator<EditedMaterial> comparator = EditedMaterialComparator.getComparator(column.getData(), order.getDir());
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
