package com.company.controllers.rest;

import com.company.models.TechTask;
import com.company.models.paging.Page;
import com.company.models.paging.PageArray;
import com.company.models.paging.PagingRequest;
import com.company.services.TechTaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("profile/database/techtask")
public class TechTaskRestController {
    @Autowired
    private TechTaskService techTaskService;

    @PostMapping
    public Page<TechTask> list(@RequestBody PagingRequest pagingRequest) {
        return techTaskService.getTechTasks(pagingRequest);
    }

    @PostMapping("/array")
    public PageArray array(@RequestBody PagingRequest pagingRequest) {
        return techTaskService.getTechTaskArray(pagingRequest);
    }

    @PostMapping("/delete")
    public void delete(@RequestParam("techtask_id") Integer id) {
        techTaskService.delete(id);
    }

    @PostMapping("/update")
    public void update(@RequestParam("techtask_id") Integer id,
                       @RequestParam("link") String link) {
        techTaskService.update(id, link);
    }

    @PostMapping("/add")
    public void add(@RequestParam("link") String link) {
        techTaskService.add(link);
    }
}
