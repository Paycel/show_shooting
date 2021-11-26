package com.company.services;

import com.company.models.TechTask;
import com.company.models.comparators.TechTaskComparator;
import com.company.models.paging.*;
import com.company.repositories.TechTaskRepository;
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
public class TechTaskService {
    private static final Comparator<TechTask> EMPTY_COMPARATOR = (e1, e2) -> 0;

    @Autowired
    private TechTaskRepository repository;

    public List<TechTask> findAll() {
        return repository.findAll();
    }

    public void delete(Integer id) {
        repository.deleteById(id);
    }

    public TechTask findById(Integer id) {
        return repository.findById(id).get();
    }

    public void update(Integer id,
                       String link) {
        TechTask techtask = repository.findById(id).get();
        techtask.setTaskLink(link);
        repository.save(techtask);
    }

    public void add(String link) {
        TechTask techtask = new TechTask(link);
        repository.save(techtask);
    }

    public PageArray getTechTaskArray(PagingRequest pagingRequest) {
        pagingRequest.setColumns(Stream.of("id", "link")
                .map(Column::new)
                .collect(Collectors.toList()));
        Page<TechTask> techTaskPage = getTechTasks(pagingRequest);

        PageArray pageArray = new PageArray();
        pageArray.setRecordsFiltered(techTaskPage.getRecordsFiltered());
        pageArray.setRecordsTotal(techTaskPage.getRecordsTotal());
        pageArray.setDraw(techTaskPage.getDraw());
        pageArray.setData(techTaskPage.getData()
                .stream()
                .map(this::toStringList)
                .collect(Collectors.toList()));
        return pageArray;
    }

    private List<String> toStringList(TechTask techtask) {
        return Arrays.asList(
                techtask.getId().toString(),
                techtask.getTaskLink()
        );
    }

    public Page<TechTask> getTechTasks(PagingRequest pagingRequest) {
        List<TechTask> techTasks = findAll();
        return getPage(techTasks, pagingRequest);
    }

    private Page<TechTask> getPage(List<TechTask> techTasks, PagingRequest pagingRequest) {
        List<TechTask> filtered = techTasks.stream()
                .sorted(sortTechTasks(pagingRequest))
                .filter(filterTechTasks(pagingRequest))
                .skip(pagingRequest.getStart())
                .limit(pagingRequest.getLength())
                .collect(Collectors.toList());

        long count = techTasks.stream()
                .filter(filterTechTasks(pagingRequest))
                .count();

        Page<TechTask> page = new Page<>(filtered);
        page.setRecordsFiltered((int) count);
        page.setRecordsTotal((int) count);
        page.setDraw(pagingRequest.getDraw());

        return page;
    }

    private Predicate<TechTask> filterTechTasks(PagingRequest pagingRequest) {
        if (pagingRequest.getSearch() == null || StringUtils.isEmpty(pagingRequest.getSearch()
                .getValue())) {
            return techtask -> true;
        }

        String value = pagingRequest.getSearch()
                .getValue();

        return techtask -> techtask.getId().toString()
                .contains(value)
                || techtask.getTaskLink()
                .toLowerCase()
                .contains(value);
    }

    private Comparator<TechTask> sortTechTasks(PagingRequest pagingRequest) {
        if (pagingRequest.getOrder() == null) {
            return EMPTY_COMPARATOR;
        }

        try {
            Order order = pagingRequest.getOrder()
                    .get(0);

            int columnIndex = order.getColumn();
            Column column = pagingRequest.getColumns()
                    .get(columnIndex);

            Comparator<TechTask> comparator = TechTaskComparator.getComparator(column.getData(), order.getDir());
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
