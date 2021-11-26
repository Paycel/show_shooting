package com.company.controllers.rest;

import com.company.models.Scenario;
import com.company.models.paging.Page;
import com.company.models.paging.PageArray;
import com.company.models.paging.PagingRequest;
import com.company.services.ScenarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("profile/database/scenario")
public class ScenarioRestController {
    @Autowired
    private ScenarioService scenarioService;

    @PostMapping
    public Page<Scenario> list(@RequestBody PagingRequest pagingRequest) {
        return scenarioService.getScenario(pagingRequest);
    }

    @PostMapping("/array")
    public PageArray array(@RequestBody PagingRequest pagingRequest) {
        return scenarioService.getScenarioArray(pagingRequest);
    }

    @PostMapping("/delete")
    public void delete(@RequestParam("scenario_id") Integer id) {
        scenarioService.delete(id);
    }

    @PostMapping("/update")
    public void update(@RequestParam("scenario_id") Integer id,
                       @RequestParam("approval") Boolean approval,
                       @RequestParam("link") String link,
                       @RequestParam("version") Integer version) {
        scenarioService.update(id, version, approval, link);
    }

    @PostMapping("/add")
    public void add(@RequestParam("approval") Boolean approval,
                    @RequestParam("link") String link,
                    @RequestParam("version") Integer version) {
        scenarioService.add(version, approval, link);
    }
}
