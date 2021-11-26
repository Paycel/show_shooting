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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("profile/database/edited_material")
public class EditedMaterialRestController {
    @Autowired
    private EditedMaterialService editedMaterialService;

    @Autowired
    private RawMaterialService rawMaterialService;

    @PostMapping
    public Page<EditedMaterial> list(@RequestBody PagingRequest pagingRequest) {
        return editedMaterialService.getEditedMaterials(pagingRequest);
    }

    @PostMapping("/array")
    public PageArray array(@RequestBody PagingRequest pagingRequest) {
        return editedMaterialService.getEditedMaterialArray(pagingRequest);
    }

    @PostMapping("/delete")
    public void delete(@RequestParam("edited_id") Integer id) {
        editedMaterialService.delete(id);
    }

    @PostMapping("/update")
    public void update(@RequestParam("edited_id") Integer id,
                       @RequestParam("name") String name,
                       @RequestParam("duration") Integer duration,
                       @RequestParam("status") String status,
                       @RequestParam("raw_id") Integer rawId) {
        editedMaterialService.update(id, name, duration, status, rawMaterialService.findById(rawId));
    }

    @PostMapping("/add")
    public void add(@RequestParam("name") String name,
                    @RequestParam("duration") Integer duration,
                    @RequestParam("status") String status,
                    @RequestParam("raw_id") Integer rawId) {
        editedMaterialService.add(name, duration, status, rawMaterialService.findById(rawId));
    }
}
