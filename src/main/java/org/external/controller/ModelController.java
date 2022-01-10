package org.external.controller;

import org.models.core.dao.CustomRepositories;
import org.models.core.dao.ModelRepository;
import org.models.core.domain.Model;
import org.models.core.domain.ModelsFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/model")
public class ModelController {

    @Autowired
    ModelRepository modelRepository;

    @Autowired
    CustomRepositories customRepositories;

    @GetMapping("/all/modelsbymake")
    public List<Model> getModelsByMake(@RequestParam("make") String make){
        return modelRepository.findByMake(make);
    }


    @GetMapping("/{name}")
    public Model getModelByName(@PathVariable("name") String name){
        return modelRepository.findOneByName(name);
    }

    @PutMapping("/filter")
    public List<Model> getAllModelsByFilter(@RequestBody ModelsFilter filter){
        return customRepositories.getAllModels(filter);
    }

    @PostMapping
    public Model saveOrUpdate(@RequestBody Model model){
        return modelRepository.save(model);
    }
}
