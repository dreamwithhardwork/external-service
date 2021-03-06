package org.external.controller;


import org.models.core.dao.CustomRepositories;
import org.models.core.dao.MakeRepository;
import org.models.core.dao.ModelRepository;
import org.models.core.dao.VariantRepository;
import org.models.core.domain.Make;
import org.models.core.domain.Model;
import org.models.core.domain.ModelsFilter;
import org.models.core.domain.Variant;
import org.models.core.properies.VehicleProperties;
import org.models.core.validators.MakeValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.websocket.server.PathParam;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@RequestMapping("/makemodel")
@RestController
@Validated
public class MakeModelController {



    @Autowired
    MakeRepository makeRepository;

    @Autowired
    ModelRepository modelRepository;

    @Autowired
    VariantRepository variantRepository;

    @Autowired
    CustomRepositories customRepositories;

    @Autowired
    VehicleProperties properties;

    @GetMapping("/all/make")
    public List<Make>  gatAllMakeTypes(){
        return  makeRepository.findAll();
    }

    @GetMapping
    public List<Model> getAll(@RequestParam("make") String make){
        return modelRepository.findByMake(make);
    }

    @GetMapping("/all")
    public List<Model> getMakeByName(){
        return customRepositories.getAllModels();
    }

    @GetMapping("/make-model")
    public Map<String, Map<String, Set<String>>> getMakeModelList(){
        return properties.getMakemodelvariants();
    }

    @GetMapping("/model/{name}")
    public Model getModelByName(@PathVariable("name") String name){
       return customRepositories.getModelByName(name);
    }

    @PutMapping
    public List<Model> getAllModels(@RequestBody ModelsFilter filter){
        return customRepositories.getAllModels(filter);
    }
    @GetMapping("/variants")
    public List<Variant> getVariantsByModel(@RequestParam("model") String model){
        return variantRepository.findByModel(model);
    }

}
