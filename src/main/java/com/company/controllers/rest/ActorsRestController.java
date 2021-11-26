package com.company.controllers.rest;

import com.company.models.Actor;
import com.company.models.paging.Page;
import com.company.models.paging.PageArray;
import com.company.models.paging.PagingRequest;
import com.company.services.ActorService;
import com.company.services.ScenarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("profile/database/actors")
public class ActorsRestController {
    @Autowired
    private ActorService actorService;

    @Autowired
    private ScenarioService scenarioService;

    @PostMapping
    public Page<Actor> list(@RequestBody PagingRequest pagingRequest) {
        return actorService.getActors(pagingRequest);
    }

    @PostMapping("/array")
    public PageArray array(@RequestBody PagingRequest pagingRequest) {
        return actorService.getActorsArray(pagingRequest);
    }

    @PostMapping("/delete")
    public void delete(@RequestParam("actor_id") Integer id){
        actorService.delete(id);
    }

    @PostMapping("/update")
    public void update(@RequestParam("actor_id") Integer id,
                       @RequestParam("name") String name,
                       @RequestParam("age") Integer age,
                       @RequestParam("number") String number,
                       @RequestParam("role") String role,
                       @RequestParam("scenario_id") Integer scenarioID,
                       @RequestParam("denyMovie") Boolean denyMovie,
                       @RequestParam("denyScenario") Boolean denyScenario){
        actorService.update(id, name, age, number, role, scenarioService.findById(scenarioID), denyMovie, denyScenario);
    }

    @PostMapping("/add")
    public void add(@RequestParam("name") String name,
                    @RequestParam("age") Integer age,
                    @RequestParam("number") String number,
                    @RequestParam("role") String role,
                    @RequestParam("scenario_id") Integer scenarioID,
                    @RequestParam("denyMovie") Boolean denyMovie,
                    @RequestParam("denyScenario") Boolean denyScenario){
        actorService.add(name, age, number, role, scenarioService.findById(scenarioID), denyMovie, denyScenario);
    }
}
