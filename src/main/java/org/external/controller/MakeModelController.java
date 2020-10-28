package org.external.controller;


import org.models.core.dao.CustomRepositories;
import org.models.core.dao.MakeRepository;
import org.models.core.dao.ModelRepository;
import org.models.core.dao.VariantRepository;
import org.models.core.domain.Make;
import org.models.core.domain.Model;
import org.models.core.domain.Variant;
import org.models.core.properies.VehicleProperties;
import org.models.core.validators.MakeValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
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

    @GetMapping("/all/make")
    public List<Make>  gatAllMakeTypes(){
        return  makeRepository.findAll();
    }

    @GetMapping("/models")
    public List<Model> getModelsByMake(@RequestParam("make") String make){
      return modelRepository.findByMake(make);
    }

    @GetMapping("/variants")
    public List<Variant> getVariantsByModel(@RequestParam("model") String model){
        return variantRepository.findByModel(model);
    }

}
