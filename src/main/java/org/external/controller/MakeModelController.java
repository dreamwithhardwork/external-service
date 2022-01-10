package org.external.controller;


import org.models.core.dao.CustomRepositories;
import org.models.core.dao.MakeRepository;
import org.models.core.dao.ModelRepository;
import org.models.core.domain.Make;
import org.models.core.domain.Model;
import org.models.core.domain.ModelsFilter;
import org.models.core.properies.VehicleProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Set;

@RequestMapping("/makemodel")
@RestController
@Validated
public class MakeModelController {



    @Autowired
    MakeRepository makeRepository;

    @Autowired
    ModelRepository modelRepository;

    @Autowired
    CustomRepositories customRepositories;

    @Autowired
    VehicleProperties properties;

    @GetMapping("/all/make")
    public List<Make>  gatAllMakeTypes(){
        return  makeRepository.findAll();
    }

    @GetMapping("/all/modelsbymake")
    public List<Model> getModelsByMake(@RequestParam("make") String make){
        return modelRepository.findByMake(make);
    }


    @GetMapping("/model/{name}")
    public Model getModelByName(@PathVariable("name") String name){
       return modelRepository.findOneByName(name);
    }

    @GetMapping("/make/{name}")
    public Make getMakeByName(@PathVariable("name") String name){
        return makeRepository.findOneByName(name);
    }

    @PutMapping
    public List<Model> getAllModelsByFilter(@RequestBody ModelsFilter filter){
        return customRepositories.getAllModels(filter);
    }


    @PostMapping("/make")
    public Make saveOrUpdate(@RequestBody Make make){
        return makeRepository.save(make);
    }

    @PostMapping("/model")
    public Model saveOrUpdate(@RequestBody Model model){
        return modelRepository.save(model);
    }



}
