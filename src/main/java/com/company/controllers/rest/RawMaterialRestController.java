package com.company.controllers.rest;

import com.company.models.EditedMaterial;
import com.company.models.RawMaterial;
import com.company.models.Scenario;
import com.company.models.paging.Page;
import com.company.models.paging.PageArray;
import com.company.models.paging.PagingRequest;
import com.company.services.EditedMaterialService;
import com.company.services.RawMaterialService;
import com.company.services.ScenarioService;
import com.company.services.TechTaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("profile/database/raw_material")
public class RawMaterialRestController {
    @Autowired
    private ScenarioService scenarioService;

    @Autowired
    private TechTaskService techTaskService;

    @Autowired
    private RawMaterialService rawMaterialService;

    @PostMapping
    public Page<RawMaterial> list(@RequestBody PagingRequest pagingRequest) {
        return rawMaterialService.getRawMaterials(pagingRequest);
    }

    @PostMapping("/array")
    public PageArray array(@RequestBody PagingRequest pagingRequest) {
        return rawMaterialService.getRawMaterialArray(pagingRequest);
    }

    @PostMapping("/delete")
    public void delete(@RequestParam("raw_id") Integer id) {
        rawMaterialService.delete(id);
    }

    @PostMapping("/update")
    public void update(@RequestParam("raw_id") Integer id,
                       @RequestParam("name") String name,
                       @RequestParam("duration") Integer duration,
                       @RequestParam("status") String status,
                       @RequestParam("scenario_id") Integer scenarioId,
                       @RequestParam("techtask_id") Integer techTaskId) {
        rawMaterialService.update(id, name, duration, status, scenarioService.findById(scenarioId), techTaskService.findById(techTaskId));
    }

    @PostMapping("/add")
    public void add(@RequestParam("name") String name,
                    @RequestParam("duration") Integer duration,
                    @RequestParam("status") String status,
                    @RequestParam("scenario_id") Integer scenarioId,
                    @RequestParam("techtask_id") Integer techTaskId) {
        rawMaterialService.add(name, duration, status, scenarioService.findById(scenarioId), techTaskService.findById(techTaskId));
    }
}
